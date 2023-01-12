package com.euprava.euprava.controller;

import com.euprava.euprava.controller.Requests.SearchRequest;
import com.euprava.euprava.controller.Responses.SearchResponse;
import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import com.euprava.euprava.service.IA1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<ObrazacA1> createA1(@RequestBody ObrazacA1 obrazacA1) {
        return new ResponseEntity<>(a1Service.submitRequest(obrazacA1), HttpStatus.CREATED);
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

    @PostMapping(value = "searchByParam", produces = {"application/xml"})
    public ResponseEntity<SearchResponse> searchByParam(@RequestBody SearchRequest request) throws Exception{
        return new ResponseEntity<>(new SearchResponse(a1Service.searchByParam(request.getParam())), HttpStatus.OK);
    }

    @PostMapping(value = "searchMetadataByParam", produces = {"application/xml"})
    public ResponseEntity<SearchResponse> searchMetadataByParam(@RequestBody SearchRequest request) throws Exception{
        return new ResponseEntity<>(new SearchResponse(a1Service.searchMetadataByParam(request.getParam())), HttpStatus.OK);
    }

    @GetMapping(value = "/searchMetadataByLogicalParams", produces = {"application/xml"})
    public ResponseEntity<SearchResponse> searchMetadataByLogicalParams(@RequestParam(name="search") String search) throws Exception {
        return new ResponseEntity<>(new SearchResponse(a1Service.searchMetadataByLogicalParams(search)), HttpStatus.OK);
    }

}
