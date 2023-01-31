package com.euprava.euprava.service.implementation;

import com.euprava.euprava.controller.Responses.A1Response;
import com.euprava.euprava.controller.Responses.NumberResponse;
import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import com.euprava.euprava.model.a1sertifikat.StatusZahtjeva;
import com.euprava.euprava.model.a1sertifikat.TFizickiPodnosilac;
import com.euprava.euprava.model.a1sertifikat.TPravniPodnosilac;
import com.euprava.euprava.rdf.FusekiReader;
import com.euprava.euprava.rdf.FusekiWriter;
import com.euprava.euprava.rdf.MetadataExtractor;
import com.euprava.euprava.repository.A1RequestRepository;
import com.euprava.euprava.service.IA1Service;
import com.euprava.euprava.transformation.HTMLTransformer;
import com.euprava.euprava.transformation.XSLFOTransformer;
import com.euprava.euprava.util.EmailService;
import com.euprava.euprava.util.SchemaValidationHandler;
import com.euprava.euprava.util.Utility;
import com.euprava.euprava.util.exception.customExceptions.InvalidRequestException;
import com.euprava.euprava.util.exception.customExceptions.UnsupportedTypeException;
import lombok.RequiredArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.euprava.euprava.util.Utility.getFileName;
import static com.euprava.euprava.util.Utility.saveFile;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Service
public class A1ServiceImpl implements IA1Service {

    private final A1RequestRepository a1RequestRepository;

    private final XSLFOTransformer xslfoTransformer;

    private final HTMLTransformer htmlTransformer;

    private final MetadataExtractor metadataExtractor;

    private final EmailService emailService;

    private final List<String> supportedMetadataTypes = Arrays.asList("RDF/JSON", "RDF/XML", "N-TRIPLE");

    @Override
    public ObrazacA1 getObrazacById(String id) throws Exception {
        return a1RequestRepository.findById("/db/a1", "id_" + id);
    }

    @Override
    public ObrazacA1 saveA1Request(ObrazacA1 request) {

        if (request.getIdKlijenta() == 0) {
            throw new InvalidRequestException("Missing client id");
        }
        try {
            long id = Utility.getNextId();
            request.setId(id);

            request.setAbout("http://euprava.euprava.com/model/rdf/a1Sertifikat/" + id);
            request.setTypeof("pred:IdentifikatorDokumenta");

            request.setDatumPodnosenja(new ObrazacA1.DatumPodnosenja());
            DatatypeFactory df = DatatypeFactory.newInstance();
            XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar();
            xmlCalendar.setYear(GregorianCalendar.getInstance().get(Calendar.YEAR));
            xmlCalendar.setMonth(GregorianCalendar.getInstance().get(Calendar.MONTH) + 1);
            xmlCalendar.setDay(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));

            request.getDatumPodnosenja().setValue(xmlCalendar);
            request.getDatumPodnosenja().setProperty("pred:PodnesenDatuma");
            request.getDatumPodnosenja().setDatatype("xs:string");

            request.getDjelo().getNaslov().setProperty("pred:Naslov");
            request.getDjelo().getNaslov().setDatatype("xs:string");

            request.getDjelo().getVrstaDjela().setProperty("pred:Vrsta");
            request.getDjelo().getVrstaDjela().setDatatype("xs:string");

            request.getDjelo().getFormaZapisa().setProperty("pred:Forma");
            request.getDjelo().getFormaZapisa().setDatatype("xs:string");

            request.getPodnosilac().getEmail().setProperty("pred:PodnioEmail");
            request.getPodnosilac().getEmail().setDatatype("xs:string");

            request.setStatus(new ObrazacA1.Status());
            request.getStatus().setValue(StatusZahtjeva.PODNESEN);
            request.getStatus().setProperty("pred:Status");
            request.getStatus().setDatatype("xs:string");

            request.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://euprava.euprava.com/model/rdf/a1Sertifikat/predicate/");
            request.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
            a1RequestRepository.save("/db/a1", "id_" + request.getId(), request);

            XMLResource resource = a1RequestRepository.loadXmlResource("/db/a1", "id_" + request.getId());
            byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
            FusekiWriter.saveRDF(new ByteArrayInputStream(out), "a1Sertifikat");
            emailService.sendEmailWithAttachment(request.getPodnosilac().getEmail().getValue(), getPDFFileById(String.valueOf(request.getId())));

            return request;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ObrazacA1 submitRequest(ObrazacA1 obrazacA1) {
        return this.saveA1Request(obrazacA1);
    }

    @Override
    public String uploadDescriptionFile(MultipartFile file) {
        try {
            String fileName = getFileName(file, true);
            saveFile("src/main/resources/data/a1requests/descriptionFiles/" + fileName, file.getBytes());
            return fileName;
        } catch (IOException | NullPointerException exception) {
            System.out.println(exception.getMessage());
            return "";
        }
    }

    @Override
    public String uploadExampleFile(MultipartFile file) {
        try {
            String fileName = getFileName(file, false);
            saveFile("src/main/resources/data/a1requests/exampleFiles/" + fileName, file.getBytes());
            return fileName;
        } catch (IOException | NullPointerException exception) {
            System.out.println(exception.getMessage());
            return "";
        }
    }

    @Override
    public List<ObrazacA1> searchByParam(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/param_search.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, param);
        System.out.println(formattedXQueryExpression);

        List<ObrazacA1> documentList = new ArrayList<>();

        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        for (Resource resource : resources) {
            documentList.add(unmarshallXMLResource((XMLResource) resource));
        }
        return documentList;
    }

    @Override
    public List<ObrazacA1> searchMetadataByParam(String param) throws Exception {
        String sparqlCondition = "?document ?d \"" + param + "\" .";
        List<RDFNode> nodes = new ArrayList<>();
        List<ObrazacA1> documents = new ArrayList<>();
        ResultSet resultSet = FusekiReader.readRDFWithQuery("a1Sertifikat", sparqlCondition);
        List<String> columnNames = resultSet.getResultVars();
        while (resultSet.hasNext()) {
            QuerySolution row = resultSet.nextSolution();
            String columnName = columnNames.get(0);
            nodes.add(row.get(columnName));
        }
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            documents.add(getObrazacById(parts[parts.length - 1]));
        }
        return documents;
    }

    @Override
    public List<A1Response> searchMetadataByLogicalParams(String search) throws Exception {
        String query = generateLogicalQuery(search);
        List<RDFNode> nodes = new ArrayList<>();
        List<A1Response> responseList = new ArrayList<>();
        ResultSet resultSet = FusekiReader.readRDFWithQuery("a1Sertifikat", query);
        List<String> columnNames = resultSet.getResultVars();
        while (resultSet.hasNext()) {
            QuerySolution row = resultSet.nextSolution();
            String columnName = columnNames.get(0);
            nodes.add(row.get(columnName));
        }
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ObrazacA1 tempRequest = getObrazacById(parts[parts.length - 1]);
            String submitterName = "";
            if (tempRequest.getPodnosilac() instanceof TPravniPodnosilac) {
                submitterName = ((TPravniPodnosilac) tempRequest.getPodnosilac()).getPoslovnoIme();
            } else if (tempRequest.getPodnosilac() instanceof TFizickiPodnosilac) {
                submitterName = ((TFizickiPodnosilac) tempRequest.getPodnosilac()).getPodaciOsoba().getIme() + " " + ((TFizickiPodnosilac) tempRequest.getPodnosilac()).getPodaciOsoba().getPrezime();
            }
            A1Response response = new A1Response(tempRequest.getId(), submitterName, tempRequest.getPodnosilac().getEmail().getValue(),"A1", tempRequest.getDatumPodnosenja().getValue().toString(), tempRequest.getStatus().getValue().toString());
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public String getMetadata(String id, String type) throws IOException {
        if (!supportedMetadataTypes.contains(type)) {
            throw new UnsupportedTypeException("Unsupported metadata type.");
        }
        String sparqlCondition = "<http://euprava.euprava.com/model/rdf/a1Sertifikat/" + id + "> ?d ?s .";
        return FusekiReader.readMetadata("/a1Sertifikat", sparqlCondition, type);
    }

    @Override
    public File getPDFFileById(String id) throws Exception {
        String document = a1RequestRepository.getObrazacAsStringById(id);
        return xslfoTransformer.getPdfFile(document, "src/main/resources/data/xsl_fo/a1-fo.xsl", "src/main/resources/data/gen/pdf/temp.pdf");
    }

    @Override
    public File getHTMLFileById(String id) throws Exception {
        a1RequestRepository.saveTempXml(id);
        String path_html = "src/main/resources/data/gen/html/temp.html";
        return new File(htmlTransformer.generateHTML("src/main/resources/data/gen/temp.xml", "src/main/resources/data/xslt/a1.xsl", path_html));
    }

    @Override
    public ObrazacA1 approveRequest(String id, int code, long idRjesenja) throws Exception {
        a1RequestRepository.approveRequest("/db/a1", "id_" + id, code, idRjesenja);
        return getObrazacById(id);
    }

    @Override
    public ObrazacA1 declineRequest(String id, long idRjesenja) throws Exception {
        a1RequestRepository.declineRequest("/db/a1", "id_" + id, idRjesenja);
        return getObrazacById(id);
    }

    @Override
    public List<A1Response> getClientRequests(long clientId) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/client_id.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, clientId);
        System.out.println(formattedXQueryExpression);

        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        return getResponseListFromResource(resources);
    }

    @Override
    public List<A1Response> searchClientByParam(long clientId, String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/param_search_client.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, param, clientId);
        System.out.println(formattedXQueryExpression);

        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        return getResponseListFromResource(resources);
    }

    @Override
    public List<A1Response> searchEmployeeByParam(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/param_search.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, param);
        System.out.println(formattedXQueryExpression);

        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        return getResponseListFromResource(resources);
    }

    @Override
    public List<A1Response> getRequests() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/all.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);


        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", xqueryExpression);

        return getResponseListFromResource(resources);
    }

    @Override
    public NumberResponse getNumberOfRequests(String start, String end) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/date.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, start, end);
        System.out.println(formattedXQueryExpression);

        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        return getNumberResponseFromResources(resources);
    }

    @Override
    public List<A1Response> searchEmployeeByReference(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String queryPath = "src/main/resources/data/xquery/reference.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, param);
        System.out.println(formattedXQueryExpression);

        List<Resource> resources = a1RequestRepository.getObrazacByQuery("/db/a1", "http://euprava.euprava.com/model/a1Sertifikat", formattedXQueryExpression);

        return getResponseListFromResource(resources);
    }

    private NumberResponse getNumberResponseFromResources(List<Resource> resources) throws JAXBException, XMLDBException, SAXException {
        int podnesenih = 0;
        int odbijenih = 0;
        int odobrenih = 0;
        for (Resource resource : resources) {
            ObrazacA1 tempRequest = unmarshallXMLResource((XMLResource) resource);

            if (tempRequest.getStatus().getValue().equals(StatusZahtjeva.ODOBREN)) {
                odobrenih++;
            } else if (tempRequest.getStatus().getValue().equals(StatusZahtjeva.ODBIJEN)) {
                odbijenih++;
            } else {
                podnesenih++;
            }
        }

        return new NumberResponse(podnesenih, odbijenih, odobrenih);
    }

    private List<A1Response> getResponseListFromResource(List<Resource> resources) throws JAXBException, XMLDBException, SAXException {
        List<A1Response> responseList = new ArrayList<>();
        for (Resource resource : resources) {
            ObrazacA1 tempRequest = unmarshallXMLResource((XMLResource) resource);
            String submitterName = "";
            if (tempRequest.getPodnosilac() instanceof TPravniPodnosilac) {
                submitterName = ((TPravniPodnosilac) tempRequest.getPodnosilac()).getPoslovnoIme();
            } else if (tempRequest.getPodnosilac() instanceof TFizickiPodnosilac) {
                submitterName = ((TFizickiPodnosilac) tempRequest.getPodnosilac()).getPodaciOsoba().getIme() + " " + ((TFizickiPodnosilac) tempRequest.getPodnosilac()).getPodaciOsoba().getPrezime();
            }
            A1Response response = new A1Response(tempRequest.getId(), submitterName, tempRequest.getPodnosilac().getEmail().getValue(), "A1", tempRequest.getDatumPodnosenja().getValue().toString(), tempRequest.getStatus().getValue().toString());
            responseList.add(response);
        }
        return responseList;
    }

    private String generateLogicalQuery(String search) {
        if (search.contains("AND")) {
            return generateANDQuery(search);
        } else if (search.contains("OR")) {
            return generateORQuery(search);
        } else if (search.contains("NOT")) {
            return generateNOTQuery(search);
        }
        return "?document ?d \"" + search + "\" .";
    }

    private String generateANDQuery(String search) {
        String[] parts = search.split("AND");
        if (parts.length == 1) {
            return "?document ?d \"" + parts[0] + "\" .";
        }
        String query = "";

        for (int i = 0; i < parts.length; i++) {
            query = query + "\n ?document ?" + i + " \"" + parts[i] + "\" . ";
        }
        return query;
    }

    private String generateORQuery(String search) {
        String[] parts = search.split("OR");
        if (parts.length == 1) {
            return "?document ?d \"" + parts[0] + "\" .";
        }
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            value.append(parts[i]).append("|");
        }
        value.append(parts[parts.length - 1]);
        return "?document ?p ?o . FILTER (REGEX(?o,\"" + value + "\"))";
    }

    private String generateNOTQuery(String search) {
        String[] parts = search.split("NOT");
        StringBuilder value = new StringBuilder();
        if (parts.length == 1) {
            value.append(parts[0]);
        } else {
            for (int i = 0; i < parts.length - 1; i++) {
                value.append(parts[i]).append("|");
            }
            value.append(parts[parts.length - 1]);
        }

        return "GRAPH <http://localhost:3031/a1/data/a1Sertifikat> {?obrazacA1 <http://euprava.euprava.com/model/rdf/a1Sertifikat/predicate/Naslov> ?value.} FILTER NOT EXISTS {  FILTER (?value != \"Gage Perez\")}";
    }//?document ?p ?o . FILTER CONTAI(REGEX(?o,"Pisano|Scensko"))

    private ObrazacA1 unmarshallXMLResource(XMLResource resource) throws JAXBException, SAXException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File("src/main/resources/data/schemas/a1_shema.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new SchemaValidationHandler());
        return (ObrazacA1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }

}
