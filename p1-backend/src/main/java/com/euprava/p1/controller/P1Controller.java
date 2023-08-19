package com.euprava.p1.controller;

import com.euprava.p1.controller.Responses.ObrazacP1SearchResponse;
import com.euprava.p1.controller.Responses.ObrazacP1SearchResponseList;
import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.model.ZahtevZaPriznanjePatenta;
import com.euprava.p1.service.P1Service;
import com.itextpdf.text.DocumentException;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/p1")
@CrossOrigin
public class P1Controller {
    @Autowired
    private P1Service p1Service;

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<Void> postZahtevZaPriznanjePatenta(@RequestBody ZahtevZaPriznanjePatenta zahtev) throws DatatypeConfigurationException, JAXBException, XMLDBException, NotFoundException, IOException, ClassNotFoundException, InvocationTargetException, TransformerException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        p1Service.createObrazacP1(zahtev);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{documentId}", produces = {"application/xml"})
    public ResponseEntity<ObrazacP1> getObrazacP1(@PathVariable String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException {
        ObrazacP1 obrazacP1 = p1Service.retrieveObrazacP1(documentId);
        return new ResponseEntity<>(obrazacP1, HttpStatus.OK);
    }

    @GetMapping(value = "/{documentId}/pdf")
    public ResponseEntity<Resource> getAsPDF(@PathVariable String documentId) throws IOException, DocumentException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File obrazacP1PDFFile = p1Service.retrieveObrazacP1AsPDF(documentId);
        if (obrazacP1PDFFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "obrazac_P-1_" + documentId + ".pdf");

        return new ResponseEntity<>(new FileSystemResource(obrazacP1PDFFile), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{documentId}/html")
    public ResponseEntity<Resource> getAsHTML(@PathVariable String documentId) throws IOException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File obrazacP1HTMLFile = p1Service.retrieveObrazacP1AsHTML(documentId);
        if (obrazacP1HTMLFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "obrazac_P-1_" + documentId + ".html");

        return new ResponseEntity<>(new FileSystemResource(obrazacP1HTMLFile), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/metadata/{documentId}/rdf", produces = "application/rdf+xml")
    public ResponseEntity<String> getMetadataAsRDF(@PathVariable String documentId) throws IOException {
        String obrazacP1RDFMetadata = p1Service.retrieveObrazacP1MetadataAsRDF(documentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "obrazac_P-1_" + documentId + "metadata.rdf");

        return new ResponseEntity<>(obrazacP1RDFMetadata, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/metadata/{documentId}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMetadataAsJSON(@PathVariable String documentId) throws IOException {
        String obrazacP1JSONMetadata = p1Service.retrieveObrazacP1MetadataAsJSON(documentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "obrazac_P-1_" + documentId + "metadata.json");

        return new ResponseEntity<>(obrazacP1JSONMetadata, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/search", produces = {"application/xml"})
    public ResponseEntity<ObrazacP1SearchResponseList> searchByText(@RequestBody String queryText) throws XMLDBException, JAXBException {
//        ObrazacP1SearchResponseList obrazacP1SearchResponseList = p1Service.retrieveObrazacP1SearchResponseListByText(queryText);
        ObrazacP1SearchResponseList obrazacP1SearchResponseList = new ObrazacP1SearchResponseList();
        return new ResponseEntity<>(obrazacP1SearchResponseList, HttpStatus.OK);
    }

    @PostMapping(value = "/search/odobren", produces = {"application/xml"})
    public ResponseEntity<ObrazacP1SearchResponseList> searchOdobreniByText(@RequestBody String queryText) throws XMLDBException, JAXBException {
//        ObrazacP1SearchResponseList obrazacP1SearchResponseList = p1Service.retrieveObrazacP1SearchResponseListByTextAndStatusOdobren(queryText);
        ObrazacP1SearchResponseList obrazacP1SearchResponseList = new ObrazacP1SearchResponseList();
        return new ResponseEntity<>(obrazacP1SearchResponseList, HttpStatus.OK);
    }

    @PutMapping(value = "/{documentId}/odobri")
    public ResponseEntity<Void> approveObrazac(@PathVariable String documentId) throws XMLDBException {
        p1Service.setObrazacP1StatusAsOdobren(documentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{documentId}/odbij")
    public ResponseEntity<Void> declineObrazac(@PathVariable String documentId) throws XMLDBException {
        p1Service.setObrazacP1StatusAsOdbijen(documentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
