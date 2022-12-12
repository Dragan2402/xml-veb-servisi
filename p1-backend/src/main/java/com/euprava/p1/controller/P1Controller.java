package com.euprava.p1.controller;

import com.euprava.p1.model.ObrazacP1;
import com.euprava.p1.service.P1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping(value = "api/p1")
@CrossOrigin
public class P1Controller {
    @Autowired
    private P1Service p1Service;

    @GetMapping(value = "/{fileName}")
    public ResponseEntity<ObrazacP1> getObrazacP1(@PathVariable(value = "fileName") String fileName) throws JAXBException, IOException {
        ObrazacP1 p1 = p1Service.getObrazacP1(fileName);
        return new ResponseEntity<>(p1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ObrazacP1> getExampleObrazacP1() throws JAXBException, IOException {
        ObrazacP1 p1 = p1Service.getObrazacP1("p1_example.xml");
        return new ResponseEntity<>(p1, HttpStatus.OK);
    }
}
