package com.euprava.z1.controller;


import com.euprava.z1.model.ZahtevZaPriznanjeZiga;
import com.euprava.z1.service.Z1Service;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "api/z1")
@CrossOrigin
public class Z1Controller {
    @Autowired
    private Z1Service z1Service;

    @GetMapping(value = "/{fileName}", produces = {"application/xml"})
    public ResponseEntity<ZahtevZaPriznanjeZiga> getZahtevZaPriznanjeZiga(@PathVariable String fileName) throws JAXBException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException, IOException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = z1Service.getZahtevZaPriznanjeZiga(fileName);
        return new ResponseEntity<>(zahtevZaPriznanjeZiga, HttpStatus.OK);
    }

}
