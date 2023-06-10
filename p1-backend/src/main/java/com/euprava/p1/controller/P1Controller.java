package com.euprava.p1.controller;

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
import java.nio.file.Files;

@RestController
@RequestMapping(value = "api/p1")
@CrossOrigin
public class P1Controller {
    @Autowired
    private P1Service p1Service;

//    @PostMapping(produces = {"application/xml"})
//    public ResponseEntity<String> postObrazacP1(@RequestBody ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
//        String documentId = p1Service.createObrazacP1(obrazacP1);
//        return new ResponseEntity<>(documentId, HttpStatus.CREATED);
//    }

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<Void> postZahtevZaPriznanjePatenta(@RequestBody ZahtevZaPriznanjePatenta zahtev) {
        System.out.println(zahtev.getNazivPronalaska().getNaSrpskom().getValue());
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
}
