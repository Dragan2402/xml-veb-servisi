package com.euprava.z1.controller;


import com.euprava.z1.model.Z1;
import com.euprava.z1.service.Z1Service;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "api/z1")
@CrossOrigin
public class Z1Controller {
    @Autowired
    private Z1Service z1Service;

//    @PostMapping(produces = {"application/xml"})
//    public ResponseEntity<String> postZ1(@RequestBody Z1 zahtevZaPriznanjeZiga) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
//        String documentId = z1Service.createZ1(zahtevZaPriznanjeZiga);
//        return new ResponseEntity<>(documentId, HttpStatus.CREATED);
//    }

    @GetMapping(value = "/{fileName}", produces = {"application/xml"})
    public ResponseEntity<Z1> getZ1(@PathVariable String fileName) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException, IOException {
        Z1 z1 = z1Service.getZ1(fileName);
        return new ResponseEntity<>(z1, HttpStatus.OK);
    }

}
