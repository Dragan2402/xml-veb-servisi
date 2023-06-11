package com.euprava.z1.controller;


import com.euprava.z1.controller.response.Z1ResponseList;
import com.euprava.z1.model.Z1;
import com.euprava.z1.service.Z1Service;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping(value = "api/z1")
@CrossOrigin
public class Z1Controller {
    @Autowired
    private Z1Service z1Service;

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<String> postZ1(@RequestBody Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
        String documentId = z1Service.createZ1(z1);
        return new ResponseEntity<>(documentId, HttpStatus.CREATED);
    }

    @GetMapping(value = "getAllZ1", produces = {"application/xml"})
    public ResponseEntity<Z1ResponseList> getAllZ1() throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException, IOException {
        Z1ResponseList response = new Z1ResponseList(z1Service.getAllZ1());
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{documentId}/pdf")
    public ResponseEntity<Resource> getAsPDF(@PathVariable String documentId) throws Exception {
        File obrazacP1PDFFile = z1Service.retrieveZ1AsPDF(documentId);
        if (obrazacP1PDFFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Z1_" + documentId + ".pdf");
        return new ResponseEntity<>(new FileSystemResource(obrazacP1PDFFile), headers, HttpStatus.OK);
    }

}
