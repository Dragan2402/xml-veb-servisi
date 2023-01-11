package com.euprava.euprava.controller;

import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import com.euprava.euprava.service.IA1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/a1")
@CrossOrigin
public class A1Controller {

    @Autowired
    private IA1Service a1Service;

    @GetMapping(produces = {"application/xml"})
    public ResponseEntity<ObrazacA1> getA1(@RequestParam("id") String id) throws Exception {
        return new ResponseEntity<>(a1Service.getObrazacById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createA1(@RequestBody ObrazacA1 obrazacA1) {
        long id = a1Service.submitRequest(obrazacA1);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping(value = "/uploadDescriptionFile",produces = "text/plain")
    public ResponseEntity<String> uploadDescriptionFile(@RequestBody MultipartFile file)  {
        String fileName = this.a1Service.uploadDescriptionFile(file);
        if(fileName.compareTo("")==0){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fileName,HttpStatus.CREATED);
    }

    @GetMapping(value = "generatePdf")
    public void generatePdf(@RequestParam("id") String id) throws Exception{
        this.a1Service.generatePDF(id);
    }

    @GetMapping(value = "generateXHTML")
    public void generateXHTML(@RequestParam("id") String id) throws Exception{
        this.a1Service.generateXHTML(id);
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
