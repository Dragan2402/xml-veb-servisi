package com.euprava.euprava.controller;

import com.euprava.euprava.controller.Requests.SearchRequest;
import com.euprava.euprava.controller.Responses.A1Response;
import com.euprava.euprava.controller.Responses.A1ResponseList;
import com.euprava.euprava.controller.Responses.NumberResponse;
import com.euprava.euprava.controller.Responses.SearchResponse;
import com.euprava.euprava.model.a1sertifikat.ObrazacA1;
import com.euprava.euprava.service.IA1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.File;
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

    @GetMapping(value = "getClientRequests", produces = {"application/xml"})
    public ResponseEntity<A1ResponseList> getClientRequests(@RequestParam("clientId") long clientId) throws Exception{
        return new ResponseEntity<>(new A1ResponseList(a1Service.getClientRequests(clientId)), HttpStatus.OK);
    }

    @GetMapping(value = "getRequests", produces = {"application/xml"})
    public ResponseEntity<A1ResponseList> getRequests() throws Exception{
        return new ResponseEntity<>(new A1ResponseList(a1Service.getRequests()), HttpStatus.OK);
    }
    @GetMapping(value= "approveRequest",produces = {"application/xml"})
    public ResponseEntity<ObrazacA1> approveRequest(@RequestParam("id") String id, @RequestParam("code") int code, @RequestParam("idRjesenja") long idRjesenja) throws Exception {
        return new ResponseEntity<>(a1Service.approveRequest(id, code, idRjesenja), HttpStatus.OK);
    }

    @GetMapping(value= "declineRequest",produces = {"application/xml"})
    public ResponseEntity<ObrazacA1> declineRequest(@RequestParam("id") String id, @RequestParam("idRjesenja") long idRjesenja) throws Exception {
        return new ResponseEntity<>(a1Service.declineRequest(id, idRjesenja), HttpStatus.OK);
    }

    @GetMapping(value= "getNumberOfRequestsForReport",produces = {"application/xml"})
    public ResponseEntity<NumberResponse> getNumberOfRequestsForReport(@RequestParam("start") String start, @RequestParam("end") String end) throws Exception {
        return new ResponseEntity<>(a1Service.getNumberOfRequests(start, end), HttpStatus.OK);
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

    @PostMapping(value = "searchClientByParam", produces = {"application/xml"})
    public ResponseEntity<A1ResponseList> searchClientByParam(@RequestParam("clientId") long clientId, @RequestBody SearchRequest request) throws Exception{
        return new ResponseEntity<>(new A1ResponseList(a1Service.searchClientByParam(clientId, request.getParam())), HttpStatus.OK);
    }

    @PostMapping(value = "searchEmployeeByParam", produces = {"application/xml"})
    public ResponseEntity<A1ResponseList> searchEmployeeByParam( @RequestBody SearchRequest request) throws Exception{
        return new ResponseEntity<>(new A1ResponseList(a1Service.searchEmployeeByParam(request.getParam())), HttpStatus.OK);
    }

    @PostMapping(value = "searchEmployeeByReference", produces = {"application/xml"})
    public ResponseEntity<A1ResponseList> searchEmployeeByReference( @RequestBody SearchRequest request) throws Exception{
        return new ResponseEntity<>(new A1ResponseList(a1Service.searchEmployeeByReference(request.getParam())), HttpStatus.OK);
    }

    @PostMapping(value = "searchMetadataByParam", produces = {"application/xml"})
    public ResponseEntity<SearchResponse> searchMetadataByParam(@RequestBody SearchRequest request) throws Exception{
        return new ResponseEntity<>(new SearchResponse(a1Service.searchMetadataByParam(request.getParam())), HttpStatus.OK);
    }

    @PostMapping(value = "/searchMetadataByLogicalParams", produces = {"application/xml"})
    public ResponseEntity<A1ResponseList> searchMetadataByLogicalParams(@RequestBody SearchRequest request) throws Exception {
        return new ResponseEntity<>(new A1ResponseList(a1Service.searchMetadataByLogicalParams(request.getParam())), HttpStatus.OK);
    }

    @GetMapping(value = "metadataByIdAndType")
    public ResponseEntity<String> getMetadataByIdAndType(@RequestParam(name = "id") String id, @RequestParam(name = "type") String type) throws IOException {
        return new ResponseEntity<>(a1Service.getMetadata(id, type), HttpStatus.OK);
    }

    @GetMapping(value = "getRdfMetadata", produces = "application/rdf+xml")
    public ResponseEntity<String> getRdfMetadataById(@RequestParam(name = "id") String id) throws IOException {
        String result = a1Service.getMetadata(id,"N-TRIPLE");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "file.rdf");
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping(value = "getJsonMetadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJsonMetadataById(@RequestParam(name = "id") String id) throws IOException {
        String result = a1Service.getMetadata(id,"RDF/JSON");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "file.json");
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/downloadPDFById")
    public ResponseEntity<Resource> downloadPDFFile(@RequestParam(name = "id") String id) throws Exception {
        File file = a1Service.getPDFFileById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=request_"+id+".pdf");
        return new ResponseEntity<>(new FileSystemResource(file), headers, HttpStatus.OK);
    }

    @GetMapping("/downloadHTMLById")
    public ResponseEntity<Resource> downloadHTMLFile(@RequestParam(name = "id") String id) throws Exception {
        File file = a1Service.getHTMLFileById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=request_"+id+".html");
        return new ResponseEntity<>(new FileSystemResource(file), headers, HttpStatus.OK);
    }

}
