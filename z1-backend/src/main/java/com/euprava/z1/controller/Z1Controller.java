package com.euprava.z1.controller;


import com.euprava.z1.model.ZahtevZaPriznanjeZiga;
import com.euprava.z1.service.Z1Service;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/z1")
@CrossOrigin
public class Z1Controller {
    @Autowired
    private Z1Service z1Service;

    @GetMapping(value = "/{fileName}")
    public ResponseEntity<ZahtevZaPriznanjeZiga> getZahtevZaPriznanjeZiga(@PathVariable(value = "fileName") String fileName) throws JAXBException, IOException, javax.xml.bind.JAXBException {
        ZahtevZaPriznanjeZiga z1 = z1Service.getZahtevZaPriznanjeZiga(fileName);
        return new ResponseEntity<>(z1, HttpStatus.OK);
    }
}
