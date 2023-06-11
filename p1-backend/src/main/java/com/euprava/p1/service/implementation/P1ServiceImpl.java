package com.euprava.p1.service.implementation;

import com.euprava.p1.controller.Responses.ObrazacP1SearchResponse;
import com.euprava.p1.controller.Responses.ObrazacP1SearchResponseList;
import com.euprava.p1.model.*;
import com.euprava.p1.repository.P1Repository;
import com.euprava.p1.repository.fuseki.FusekiReader;
import com.euprava.p1.service.P1Service;
import com.euprava.p1.service.transformation.pdf.PDFTransformer;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.exist.http.NotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class P1ServiceImpl implements P1Service {
    private final P1Repository p1Repository;
    private final PDFTransformer pdfTransformer;

    private static final String RDF_URL = "http://p1.euprava.com/model/rdf";


    @Override
    public String createObrazacP1(ZahtevZaPriznanjePatenta zahtev) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
        ObrazacP1 obrazacP1 = new ObrazacP1();
        obrazacP1.setZahtevZaPriznanjePatenta(zahtev);

        String documentId = generateDocumentId();

        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar();
        xmlCalendar.setDay(LocalDate.now().getDayOfMonth());
        xmlCalendar.setYear(LocalDate.now().getYear());
        xmlCalendar.setMonth(LocalDate.now().getMonthValue());

        obrazacP1.setAbout(RDF_URL + "/" + documentId);
        obrazacP1.setTypeof("pred:ObrazacP1");

        obrazacP1.setPopunjavaZavod(new PopunjavaZavod());

        obrazacP1.getPopunjavaZavod().setBrojPrijave(generateDocumentId().replace('-', '/'));

        obrazacP1.getPopunjavaZavod().setDatumPrijema(new DatumPrijema());
        obrazacP1.getPopunjavaZavod().getDatumPrijema().setValue(xmlCalendar);
        obrazacP1.getPopunjavaZavod().getDatumPrijema().setProperty("pred:DatumPrijema");
        obrazacP1.getPopunjavaZavod().getDatumPrijema().setDatatype("xs:date");

        obrazacP1.getPopunjavaZavod().setPriznatiDatumPodnosenja(xmlCalendar);

        obrazacP1.getZahtevZaPriznanjePatenta().getNazivPronalaska().getNaSrpskom().setProperty("pred:Naziv");
        obrazacP1.getZahtevZaPriznanjePatenta().getNazivPronalaska().getNaSrpskom().setDatatype("xs:string");

        obrazacP1.getPopunjavaZavod().setStatus(new Status());
        obrazacP1.getPopunjavaZavod().getStatus().setValue(TStatus.PODNESEN);
        obrazacP1.getPopunjavaZavod().getStatus().setProperty("pred:Status");
        obrazacP1.getPopunjavaZavod().getStatus().setDatatype("xs:string");

        obrazacP1.getOtherAttributes().put(QName.valueOf("xmlns:pred"), RDF_URL + "/predicate");
        obrazacP1.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        p1Repository.save(documentId, obrazacP1);

        return documentId;
    }

    @Override
    public ObrazacP1 retrieveObrazacP1(String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException {
        return p1Repository.findById(documentId);
    }

    @Override
    public File retrieveObrazacP1AsPDF(String documentId) throws IOException, DocumentException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Node documentNode = p1Repository.findByIdAsNode(documentId);
        return pdfTransformer.generatePDF(documentNode);
    }

    @Override
    public File retrieveObrazacP1AsHTML(String documentId) throws XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, FileNotFoundException {
        Node documentNode = p1Repository.findByIdAsNode(documentId);
        return pdfTransformer.generateHTML(documentNode);
    }

    @Override
    public String retrieveObrazacP1MetadataAsRDF(String documentId) throws IOException {
        String sparqlCondition = "<" + RDF_URL + "/" + documentId + "> ?d ?s .";
        return FusekiReader.readMetadataAsRDF(sparqlCondition);
    }

    @Override
    public String retrieveObrazacP1MetadataAsJSON(String documentId) throws IOException {
        String sparqlCondition = "<" + RDF_URL + "/" + documentId + "> ?d ?s .";
        return FusekiReader.readMetadataAsJSON(sparqlCondition);
    }

    @Override
    public ObrazacP1SearchResponseList retrieveObrazacP1SearchResponseListByText(String text) throws XMLDBException, JAXBException {
        if (text.equals("")) text = "en";
        String xPathExp = "/*[contains(., '" + text + "')]";
        List<ObrazacP1> obrazacP1List = p1Repository.searchByText(xPathExp);

        List<ObrazacP1SearchResponse> searchResponses = new ArrayList<>();
        for (ObrazacP1 obrazacP1: obrazacP1List) {
            searchResponses.add(new ObrazacP1SearchResponse(obrazacP1));
        }
        return new ObrazacP1SearchResponseList(searchResponses);
    }

    @Override
    public ObrazacP1SearchResponseList retrieveObrazacP1SearchResponseListByTextAndStatusOdobren(String text) throws JAXBException, XMLDBException {
        if (text.equals("")) text = "en";
//        String xPathExp = "/*[contains(., '" + text + "')]";
        String xPathExp = "//Obrazac_P1[Popunjava_zavod/Status='Odobren' and contains(., '" + text + "')]";
        List<ObrazacP1> obrazacP1List = p1Repository.searchByText(xPathExp);

        List<ObrazacP1SearchResponse> searchResponses = new ArrayList<>();
        for (ObrazacP1 obrazacP1: obrazacP1List) {
            searchResponses.add(new ObrazacP1SearchResponse(obrazacP1));
        }
        return new ObrazacP1SearchResponseList(searchResponses);
    }

    @Override
    public void setObrazacP1StatusAsOdobren(String documentId) throws XMLDBException {
        p1Repository.updateStatus(documentId, "Odobren");
    }

    @Override
    public void setObrazacP1StatusAsOdbijen(String documentId) throws XMLDBException {
        p1Repository.updateStatus(documentId, "Odbijen");
    }

    private String generateDocumentId() throws XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String currentYear = Integer.toString(LocalDate.now().getYear());
        String currentYearCode = currentYear.substring(currentYear.length() - 2);

        String documentId;
        String lastId = p1Repository.getLastId();
        if (lastId == null || !lastId.substring(lastId.length() - 2).equals(currentYearCode)) {
            return "1-" + currentYearCode;
        } else {
            String[] tokens = lastId.split("-");
            int idNumber = Integer.parseInt(tokens[0]);
            idNumber++;
            documentId = idNumber + "-" + currentYearCode;
        }

        return documentId;
    }
}
