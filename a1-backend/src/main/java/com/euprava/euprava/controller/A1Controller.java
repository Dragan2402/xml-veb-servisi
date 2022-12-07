package com.euprava.euprava.controller;

import com.euprava.euprava.model.a1Sertifikat.ObrazacA1;
import com.euprava.euprava.service.IA1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "api/a1")
@CrossOrigin
public class A1Controller {

    @Autowired
    private IA1Service a1Service;

    @GetMapping
    public ResponseEntity<ObrazacA1> getA1() throws JAXBException {
        return new ResponseEntity<>(a1Service.getExample(), HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<String> getTest(){
        return new ResponseEntity("ok",HttpStatus.OK);
    }
}
