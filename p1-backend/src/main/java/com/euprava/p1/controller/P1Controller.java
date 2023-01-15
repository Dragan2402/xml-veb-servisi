package com.euprava.p1.controller;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.service.P1Service;
import jakarta.xml.bind.JAXBException;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "api/p1")
@CrossOrigin
public class P1Controller {
    @Autowired
    private P1Service p1Service;

    @GetMapping(value = "/{documentId}")
    public ResponseEntity<ObrazacP1> getObrazacP1(@PathVariable(value = "documentId") String documentId) throws IOException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, JAXBException, NotFoundException, SAXException {
        ObrazacP1 p1 = p1Service.retrieveObrazacP1(documentId);
        return new ResponseEntity<>(p1, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<String> postObrazacP1(@RequestBody ObrazacP1 obrazacP1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        p1Service.createObrazacP1(obrazacP1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
