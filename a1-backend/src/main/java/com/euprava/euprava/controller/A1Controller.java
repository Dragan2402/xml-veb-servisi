package com.euprava.euprava.controller;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import com.euprava.euprava.service.IA1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "api/a1")
@CrossOrigin
public class A1Controller {

    @Autowired
    private IA1Service a1Service;

    @GetMapping(produces = {"application/xml"})
    public ResponseEntity<ObrazacA1> getA1() throws JAXBException {
        return new ResponseEntity<>(a1Service.getExample(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> createA1(@RequestBody Map<String, Object> obrazacA1) {
        System.out.println(obrazacA1);
        boolean status = a1Service.submitRequest(obrazacA1);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PostMapping(value = "/uploadDescriptionFile",produces = "text/plain")
    public ResponseEntity<String> uploadDescriptionFile(@RequestBody MultipartFile file)  {
        String fileName = this.a1Service.uploadDescriptionFile(file);
        if(fileName.compareTo("")==0){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fileName,HttpStatus.CREATED);
    }

    @PostMapping(value = "/uploadExampleFile",produces = "text/plain")
    public ResponseEntity<String> uploadExampleFile(@RequestBody MultipartFile file)  {
        String fileName = this.a1Service.uploadExampleFile(file);
        if(fileName.compareTo("")==0){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fileName,HttpStatus.CREATED);
    }

}
