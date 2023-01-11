package com.euprava.euprava.service.implementation;

import com.euprava.euprava.model.a1sertifikat.*;
import com.euprava.euprava.rdf.FusekiWriter;
import com.euprava.euprava.rdf.MetadataExtractor;
import com.euprava.euprava.repository.A1RequestRepository;
import com.euprava.euprava.service.IA1Service;
import com.euprava.euprava.transformation.HTMLTransformer;
import com.euprava.euprava.transformation.XSLFOTransformer;
import com.euprava.euprava.util.EmailService;
import com.euprava.euprava.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static com.euprava.euprava.util.Utility.*;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Service
public class A1ServiceImpl implements IA1Service {

    private final A1RequestRepository a1RequestRepository;

    private final XSLFOTransformer xslfoTransformer;

    private final HTMLTransformer htmlTransformer;

    private final MetadataExtractor metadataExtractor;

    private final EmailService emailService;

    @Override
    public ObrazacA1 getObrazacById(String id) throws Exception {
        //JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
        //Unmarshaller unmarshaller = context.createUnmarshaller();
        return a1RequestRepository.findById("/db/a1","id_"+id);
        //return (ObrazacA1) unmarshaller.unmarshal(new File("src/main/resources/data/schemas/ExampleA1-1.xml"));
    }

    @Override
    public ObrazacA1 createRequest(Map<String, Object> obrazacA1) {
        ObrazacA1 a1 = new ObrazacA1();
        a1.setId(Utility.getNextId());
        TPodnosilac submitter = getSubmitter((Map<String, Object>) obrazacA1.get("submitter"));
        TDjelo piece = getPiece((Map<String, Object>) obrazacA1.get("piece"));
        a1.setPodnosilac(submitter);
        a1.setDjelo(piece);
        if(obrazacA1.containsKey("attorney")){
            a1.setPunomocnik(getPerson((Map<String, Object>) obrazacA1.get("attorney")));
        }

        try {
            a1.getDatumPodnosenja().setValue(getGregorianFromString((String) obrazacA1.get("submissionDate")));

        }catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        if(obrazacA1.containsKey("descriptionId")){
            a1.setSifraOpisa((String) obrazacA1.get("descriptionId"));
        }

        if(obrazacA1.containsKey("exampleId")){
            a1.setSifraPrimjera((String) obrazacA1.get("exampleId"));
        }

        a1.setPotpis((String) obrazacA1.get("signature"));
        a1.setBrojPrijave(getNextSubmissionNumber());
        return a1;
    }

    @Override
    public long saveA1Request(ObrazacA1 request) {
        try {
            //THIS FOR SAVING TO XML FILE
//            JAXBContext context = JAXBContext.newInstance("com.euprava.euprava.model.a1sertifikat");
//
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            File file = new File(
//                    "src/main/resources/data/a1requests/requestNumber_"
//                            +request.getBrojPrijave().toString()
//                            +".xml" );
//            FileOutputStream stream = new FileOutputStream(file);
//
//            marshaller.marshal(request, stream);
//            stream.close();
            //THIS FOR SAVING TO EXIST DB
            long id = Utility.getNextId();
            BigInteger submissionNumber = Utility.getNextSubmissionNumber();
            request.setId(id);
            request.setBrojPrijave(submissionNumber);

            request.setAbout("http://euprava.euprava.com/model/rdf/a1Sertifikat/"+ id);
            request.setTypeof("pred:IdentifikatorDokumenta");

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

            request.getOtherAttributes().put(QName.valueOf("xmlns:pred"),"http://euprava.euprava.com/model/rdf/a1Sertifikat/predicate/");
            request.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
            a1RequestRepository.save("/db/a1","id_"+request.getId(),request);

            XMLResource resource = a1RequestRepository.loadXmlResource("/db/a1","id_"+request.getId());
            byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
            FusekiWriter.saveRDF(new ByteArrayInputStream(out), "a1Sertifikat");

            String pathToPdf = generatePDF(String.valueOf(request.getId()));

            emailService.sendEmailWithAttachment(request.getPodnosilac().getEmail().getValue(), pathToPdf);

            return request.getId();


        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override
    public long submitRequest(ObrazacA1 obrazacA1) {
        //ObrazacA1 a1 = this.createRequest(obrazacA1);
        return this.saveA1Request(obrazacA1);
    }

    @Override
    public String uploadDescriptionFile(MultipartFile file) {
        try {
            String fileName = getFileName(file, true);
            saveFile("src/main/resources/data/a1requests/descriptionFiles/"+fileName,file.getBytes());
            return fileName;
        }catch (IOException | NullPointerException exception){
            System.out.println(exception.getMessage());
            return "";
        }
    }

    @Override
    public String uploadExampleFile(MultipartFile file) {
        try {
            String fileName = getFileName(file, false);
            saveFile("src/main/resources/data/a1requests/exampleFiles/"+fileName,file.getBytes());
            return fileName;
        }catch (IOException | NullPointerException exception){
            System.out.println(exception.getMessage());
            return "";
        }
    }

    @Override
    public String generatePDF(String id) throws Exception {
        String path_pdf = "src/main/resources/data/gen/pdf/" + id + ".pdf";
        return xslfoTransformer.generatePDF(a1RequestRepository.getObrazacAsStringById(id), "src/main/resources/data/xsl_fo/a1-fo.xsl", path_pdf);
    }

    @Override
    public String generateXHTML(String id) throws Exception {
        String path_html = "src/main/resources/data/gen/html/" + id + ".html";
        String temp_file_path = "src/main/resources/data/gen/temp.xml";
        a1RequestRepository.saveTempXml(id, temp_file_path);
        return htmlTransformer.generateHTML(temp_file_path,"src/main/resources/data/xslt/a1.xsl", path_html);
    }



    private TDjelo getPiece(Map<String, Object> pieceMap){
        TDjelo piece = new TDjelo();
        piece.getNaslov().setValue((String) pieceMap.get("title"));
        piece.getVrstaDjela().setValue(VrstaDjela.fromValue((String) pieceMap.get("type")));
        piece.getFormaZapisa().setValue(FormaZapisa.fromValue((String) pieceMap.get("writeFrom")));
        piece.setStvorenoURadnomOdnosu((Boolean) pieceMap.get("inWorkRelationship"));
        List<Map<String, Object>> authors = (List<Map<String, Object>>) pieceMap.get("authors");
        if(authors.size() > 0) {
            piece.setPodaciAutor(getAuthorData(authors));
        }

        if(pieceMap.containsKey("originalPiece")){
            piece.setPodaciOriginalnoDjelo(getOriginalPiece((Map<String, Object>) pieceMap.get("originalPiece")));
        }

        if(pieceMap.containsKey("typeOfUse")){
            piece.setNacinKoriscenja((String) pieceMap.get("typeOfUse"));
        }

        return piece;
    }

    private TDjelo.PodaciOriginalnoDjelo getOriginalPiece(Map<String, Object> originalPieceMap){
        TDjelo.PodaciOriginalnoDjelo originalPiece = new TDjelo.PodaciOriginalnoDjelo();
        originalPiece.setNaslovOriginalnogDjela((String) originalPieceMap.get("title"));
        List<Map<String, Object>> authors = (List<Map<String, Object>>) originalPieceMap.get("authors");
        if(authors.size()==1){
            if(authors.get(0).size()==0){
                originalPiece.setNepoznatiAutor(true);
                return originalPiece;
            }
        }
        for(Map<String,Object> author : authors){
            if(author.containsKey("author")){
                TZiviAutor aliveAuthor = new TZiviAutor();
                aliveAuthor.setPodaciAutor(getPerson((Map<String, Object>) author.get("author")));
                if(author.containsKey("authorSign")){
                    aliveAuthor.setPseudonimZnakAutora((String) author.get("authorSign"));
                }
                originalPiece.getPoznatiOriginalniAutor().add(aliveAuthor);
            }else{
                TPreminuliAutor deadAuthor = new TPreminuliAutor();
                deadAuthor.setIme((String) author.get("firstName"));
                deadAuthor.setPrezime((String) author.get("lastName"));
                if(author.containsKey("authorSign")){
                    deadAuthor.setPseudonimZnakAutora((String) author.get("authorSign"));
                }
                try {
                    deadAuthor.setDatumSmrti(getGregorianFromString((String) author.get("dod")));
                }
                catch (Exception ignored){
                }
                originalPiece.getPoznatiOriginalniAutor().add(deadAuthor);
            }
        }
        return originalPiece;

    }

    private TDjelo.PodaciAutor getAuthorData(List<Map<String, Object>> authors){
        if(authors.size()==1){
            if(authors.get(0).size()==0){
                TDjelo.PodaciAutor authorData =  new TDjelo.PodaciAutor();
                authorData.setNepoznatiAutor(true);
                return authorData;
            }
        }
        TDjelo.PodaciAutor authorData = new TDjelo.PodaciAutor();
        for(Map<String,Object> author : authors){
            if(author.containsKey("author")){
                TZiviAutor aliveAuthor = new TZiviAutor();
                aliveAuthor.setPodaciAutor(getPerson((Map<String, Object>) author.get("author")));
                if(author.containsKey("authorSign")){
                    aliveAuthor.setPseudonimZnakAutora((String) author.get("authorSign"));
                }
                authorData.getPoznatiAutor().add(aliveAuthor);
            }else{
                TPreminuliAutor deadAuthor = new TPreminuliAutor();
                deadAuthor.setIme((String) author.get("firstName"));
                deadAuthor.setPrezime((String) author.get("lastName"));
                if(author.containsKey("authorSign")){
                    deadAuthor.setPseudonimZnakAutora((String) author.get("authorSign"));
                }
                try {
                    deadAuthor.setDatumSmrti(getGregorianFromString((String) author.get("dod")));
                }catch (Exception ignored){}
                authorData.getPoznatiAutor().add(deadAuthor);
            }
        }
        return authorData;
    }

    private TPodnosilac getSubmitter(Map<String, Object> submitter){
        if(submitter.containsKey("person")){
            TFizickiPodnosilac individual = new TFizickiPodnosilac();
            individual.getEmail().setValue((String) submitter.get("email"));
            individual.setTelefon((String) submitter.get("phoneNumber"));
            individual.setPodaciOsoba(getPerson((Map<String, Object>) submitter.get("person")));
            return individual;
        }
        TPravniPodnosilac legal = new TPravniPodnosilac();
        legal.getEmail().setValue((String) submitter.get("email"));
        legal.setTelefon((String) submitter.get("phoneNumber"));
        legal.setPoslovnoIme((String) submitter.get("legalName"));
        legal.setAdresa(getAddress((Map<String, Object>) submitter.get("address")));
        return legal;
    }

    private TOsoba getPerson(Map<String, Object> personMap){
        TOsoba person = new TOsoba();
        person.setIme((String) personMap.get("firstName"));
        person.setPrezime((String) personMap.get("lastName"));
        person.setAdresa(getAddress((Map<String, Object>) personMap.get("address")));
        person.setDrzavljanstvo(getCitizenship((Map<String, Object>) personMap.get("citizenship")));
        return person;
    }

    private Adresa getAddress(Map<String, Object> addressMap){
        Adresa address = new Adresa();
        address.setMjesto((String) addressMap.get("place"));
        address.setPostanskiBroj(Integer.parseInt((String) addressMap.get("zipCode")));
        address.setUlica((String) addressMap.get("street"));
        Object o = addressMap.get("number");
        String number = o.toString();
        address.setBroj(new BigInteger(number));
        return address;
    }

    private TDrzavljanstvo getCitizenship(Map<String, Object> citizenshipMap){
        if(citizenshipMap.containsKey("passport")){
            TStranoDrzavljanstvo citizenship = new TStranoDrzavljanstvo();
            citizenship.setBrojPasosa((String) citizenshipMap.get("passport"));
            return citizenship;
        }
        TDomaceDrzavljanstvo citizenship = new TDomaceDrzavljanstvo();
        citizenship.setJmbg((String) citizenshipMap.get("jmbg"));
        return citizenship;
    }

    private XMLGregorianCalendar getGregorianFromString(String dateString) throws DatatypeConfigurationException {

        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            date = format.parse(dateString);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (ParseException | DatatypeConfigurationException e) {
            e.printStackTrace();
            return DatatypeFactory.newInstance().newXMLGregorianCalendar();
        }


    }
}
