package com.euprava.z1.service.implementation;

import com.euprava.z1.controller.request.Z1ZavodRequest;
import com.euprava.z1.controller.response.NumberResponse;
import com.euprava.z1.controller.response.Z1Response;
import com.euprava.z1.controller.response.Z1ResponseList;
import com.euprava.z1.model.Datum;
import com.euprava.z1.model.Status;
import com.euprava.z1.model.Z1;
import com.euprava.z1.repository.Z1Repository;
import com.euprava.z1.repository.fuseki.FusekiReader;
import com.euprava.z1.service.Z1Service;
import com.euprava.z1.service.transformation.PDFTransformer;
import com.euprava.z1.util.SchemaValidationHandler;
import lombok.RequiredArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.exist.http.NotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class Z1ServiceImpl implements Z1Service {

    private final Z1Repository z1Repository;
    private final PDFTransformer pdfTransformer;
    private static final String RDF_URL = "http://z1.euprava.com/model/rdf";

    @Override
    public List<Z1Response> getAllZ1() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException {
        String queryPath = "src/main/resources/data/xquery/all.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        List<Resource> resources = z1Repository.getZ1ByQuery("/db/z1", "http://z1.euprava.com/model", xqueryExpression);
        return getResponseListFromResource(resources);

    }

    @Override
    public List<Z1Response> searchByReference(String param) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException {
        String queryPath = "src/main/resources/data/xquery/reference.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, param);
        List<Resource> resources = z1Repository.getZ1ByQuery("/db/z1", "http://z1.euprava.com/model", formattedXQueryExpression);
        return getResponseListFromResource(resources);
    }

    private List<Z1Response> getResponseListFromResource(List<Resource> resources) throws JAXBException, XMLDBException, SAXException {
        List<Z1Response> z1List = new ArrayList<>();
        for (Resource resource : resources) {
            long id = Long.parseLong(resource.getId().split("_")[0]);
            Z1 z1 = unmarshallXMLResource((XMLResource) resource);
            z1List.add(new Z1Response(z1, id));
        }
        return z1List;
    }

    @Override
    public Z1 getZ1ById(String id) throws Exception {
        return z1Repository.findById(id);
    }

    @Override
    public List<Z1Response> searchMetadata(String search) throws Exception {
        String query = generateLogicalQuery(search);
        List<RDFNode> nodes = new ArrayList<>();
        List<Z1Response> responseList = new ArrayList<>();
        ResultSet resultSet = FusekiReader.readRDFWithQuery(query);
        List<String> columnNames = resultSet.getResultVars();
        while (resultSet.hasNext()) {
            QuerySolution row = resultSet.nextSolution();
            String columnName = columnNames.get(0);
            nodes.add(row.get(columnName));
        }

        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            long id = Long.parseLong(parts[parts.length - 1]);
            Z1 z1 = getZ1ById(parts[parts.length - 1]);
            Z1Response response = new Z1Response(z1, id);
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
        String[] parts = search.split(" AND ");
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
        String[] parts = search.split(" OR ");
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
        String[] parts = search.split(" NOT ");
        if (parts.length == 1) {
            return "?document ?d \"" + parts[0] + "\" .";
        }
        String query = "";

        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                query += "\n ?document ?" + i + " \"" + parts[i] + "\" . ";
            } else {
                query += "\n FILTER NOT EXISTS { ?document ?" + i + " \"" + parts[i] + "\" . }";
            }
        }
        return query;
    }

    @Override
    public String createZ1(Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
        int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String documentId = String.valueOf(randomNum);
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar();
        xmlCalendar.setDay(LocalDate.now().getDayOfMonth());
        xmlCalendar.setYear(LocalDate.now().getYear());
        xmlCalendar.setMonth(LocalDate.now().getMonthValue());

        z1.setAbout(RDF_URL + "/" + documentId);
        z1.setTypeof("pred:Z1");

        String emailPodnosioca = z1.getPodnosilac().getKontakt().getEPosta().getValue();
        String emailPredstavnika = z1.getZajednickiPredstavnik().getKontakt().getEPosta().getValue();

        if (!Objects.equals(emailPodnosioca, "")) {
            z1.getPodnosilac().getKontakt().getEPosta().setProperty("pred:EmailPodnosioca");
            z1.getPodnosilac().getKontakt().getEPosta().setDatatype("xs:string");
        }

        if (!Objects.equals(emailPredstavnika, "")) {
            z1.getZajednickiPredstavnik().getKontakt().getEPosta().setProperty("pred:EmailPredstavnika");
            z1.getZajednickiPredstavnik().getKontakt().getEPosta().setDatatype("xs:string");
        }

        z1.getPunomocnik().getKontakt().getEPosta().setProperty("pred:EmailPunomocnika");
        z1.getPunomocnik().getKontakt().getEPosta().setDatatype("xs:string");

        z1.setStatus(new Status());
        z1.getStatus().setValue("PODNESEN");
        z1.getStatus().setProperty("pred:Status");
        z1.getStatus().setDatatype("xs:string");

        z1.setDatum(new Datum());
        z1.getDatum().setValue(xmlCalendar);
        z1.getDatum().setProperty("pred:Datum");
        z1.getDatum().setDatatype("xs:string");

        z1.getOtherAttributes().put(QName.valueOf("xmlns:pred"), RDF_URL + "/predicate/");
        z1.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        z1Repository.save(documentId, z1);
        return documentId;
    }

    @Override
    public File retrieveZ1AsPDF(String documentId) throws Exception {
        Node documentNode = z1Repository.findByIdAsNode(documentId);
        return pdfTransformer.generatePDF(documentNode);
    }

    @Override
    public File retrieveZ1AsHTML(String documentId) throws XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, FileNotFoundException {
        Node documentNode = z1Repository.findByIdAsNode(documentId);
        return pdfTransformer.generateHTML(documentNode);
    }

    @Override
    public Z1ResponseList retrieveZ1ResponseListByText(String text) throws XMLDBException, JAXBException {
        String xPathExp = "/*[contains(., '" + text + "')]";
        List<Z1Response> list = z1Repository.searchByText(xPathExp);
        return new Z1ResponseList(list);
    }

    @Override
    public void setZ1StatusAsOdobren(String documentId, Z1ZavodRequest z1ZavodRequest, String idResenja, String brojPrijave) throws XMLDBException {
        z1Repository.zavod(documentId, z1ZavodRequest, "<Id_Resenja>"+idResenja+"</Id_Resenja><Broj_prijave>"+brojPrijave+"</Broj_prijave>","ODOBREN");
    }

    @Override
    public void setZ1StatusAsOdbijen(String documentId, Z1ZavodRequest z1ZavodRequest, String idResenja) throws XMLDBException {
        z1Repository.zavod(documentId, z1ZavodRequest, "<Id_Resenja>"+idResenja+"</Id_Resenja>","ODBIJEN");
    }

    @Override
    public NumberResponse getNumberOfRequests(String start, String end) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException, InvocationTargetException, NoSuchMethodException {
        String queryPath = "src/main/resources/data/xquery/date.xqy";
        byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
        String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
        String formattedXQueryExpression = String.format(xqueryExpression, start, end);
        List<Resource> resources = z1Repository.getZ1ByQuery("/db/z1", "z1.euprava.com/model", formattedXQueryExpression);
        return getNumberResponseFromResources(resources);
    }

    private NumberResponse getNumberResponseFromResources(List<Resource> resources) throws JAXBException, XMLDBException, SAXException {
        int podnesenih = 0;
        int odbijenih = 0;
        int odobrenih = 0;
        for (Resource resource : resources) {
            Z1 z1 = unmarshallXMLResource((XMLResource) resource);
            if (z1.getStatus().getValue().equals("ODOBREN")) {
                odobrenih++;
            } else if (z1.getStatus().getValue().equals("ODBIJEN")) {
                odbijenih++;
            } else {
                podnesenih++;
            }
        }
        return new NumberResponse(podnesenih, odbijenih, odobrenih);
    }

    @Override
    public String retrieveObrazacZ1MetadataAsRDF(String documentId) throws IOException {
        String sparqlCondition = "<" + RDF_URL + "/" + documentId + "> ?d ?s .";
        return FusekiReader.readMetadataAsRDF(sparqlCondition);
    }

    @Override
    public String retrieveObrazacZ1MetadataAsJSON(String documentId) throws IOException {
        String sparqlCondition = "<" + RDF_URL + "/" + documentId + "> ?d ?s .";
        return FusekiReader.readMetadataAsJSON(sparqlCondition);
    }

    private Z1 unmarshallXMLResource(XMLResource resource) throws JAXBException, XMLDBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(Z1.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File("src/main/resources/data/schemas/z1_schema.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);
        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new SchemaValidationHandler());
        return (Z1) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }
}
