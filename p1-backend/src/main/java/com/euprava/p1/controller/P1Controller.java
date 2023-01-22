package com.euprava.p1.controller;

import com.euprava.p1.model.ObrazacP1;
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

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<Void> postObrazacP1(@RequestBody ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        p1Service.createObrazacP1(obrazacP1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{documentId}", produces = {"application/xml"})
    public ResponseEntity<ObrazacP1> getObrazacP1(@PathVariable String documentId) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException {
        ObrazacP1 obrazacP1 = p1Service.retrieveObrazacP1(documentId);
        return new ResponseEntity<>(obrazacP1, HttpStatus.OK);
    }

    @GetMapping(value = "/{documentId}/pdf")
    public ResponseEntity<Void> getObrazacP1AsPDF(@PathVariable String documentId) throws IOException, DocumentException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File obrazacP1PDFFile = p1Service.retrieveObrazacP1AsPDF(documentId);
//        byte[] obrazacP1PDFBytes = Files.readAllBytes(obrazacP1PDFFile.toPath());

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("filename", "p1.pdf");
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=p1_" + documentId + ".pdf");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
