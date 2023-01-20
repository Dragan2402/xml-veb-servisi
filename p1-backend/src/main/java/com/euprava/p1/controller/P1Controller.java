package com.euprava.p1.controller;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.service.P1Service;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

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
}
