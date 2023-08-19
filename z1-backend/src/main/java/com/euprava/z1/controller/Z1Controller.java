package com.euprava.z1.controller;


import com.euprava.z1.controller.request.SearchRequest;
import com.euprava.z1.controller.request.Z1ZavodRequest;
import com.euprava.z1.controller.response.NumberResponse;
import com.euprava.z1.controller.response.Z1ResponseList;
import com.euprava.z1.model.Z1;
import com.euprava.z1.service.Z1Service;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "api/z1")
@CrossOrigin
public class Z1Controller {
    @Autowired
    private Z1Service z1Service;

    @GetMapping(value= "getNumberOfRequestsForReport",produces = {"application/xml"})
    public ResponseEntity<NumberResponse> getNumberOfRequestsForReport(@RequestParam("start") String start, @RequestParam("end") String end) throws Exception {
        return new ResponseEntity<>(z1Service.getNumberOfRequests(start, end), HttpStatus.OK);
    }

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<String> postZ1(@RequestBody Z1 z1) throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, DatatypeConfigurationException, NotFoundException, IOException, TransformerException {
        String documentId = z1Service.createZ1(z1);
        return new ResponseEntity<>(documentId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllZ1", produces = {"application/xml"})
    public ResponseEntity<Z1ResponseList> getAllZ1() throws JAXBException, XMLDBException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SAXException, IOException {
        Z1ResponseList response = new Z1ResponseList(z1Service.getAllZ1());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/search", produces = {"application/xml"})
    public ResponseEntity<Z1ResponseList> searchByText(@RequestBody SearchRequest searchRequest) throws XMLDBException, JAXBException {
        Z1ResponseList z1ResponseList = z1Service.retrieveZ1ResponseListByText(searchRequest.getParam());
        return new ResponseEntity<>(z1ResponseList, HttpStatus.OK);
    }

    @PostMapping(value = "/searchMetadata", produces = {"application/xml"})
    public ResponseEntity<Z1ResponseList> searchMetadata(@RequestBody SearchRequest searchRequest) throws Exception {
        return new ResponseEntity<>(new Z1ResponseList(z1Service.searchMetadata(searchRequest.getParam())), HttpStatus.OK);
    }

    @PostMapping(value = "searchByReference", produces = {"application/xml"})
    public ResponseEntity<Z1ResponseList> searchEmployeeByReference(@RequestBody SearchRequest searchRequest) throws Exception{
        return new ResponseEntity<>(new Z1ResponseList(z1Service.searchByReference(searchRequest.getParam())), HttpStatus.OK);
    }

    @GetMapping(value = "/{documentId}/html")
    public ResponseEntity<Resource> getAsHTML(@PathVariable String documentId) throws IOException, XMLDBException, NotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File Z1HTMLFile = z1Service.retrieveZ1AsHTML(documentId);
        if (Z1HTMLFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Z1_" + documentId + ".html");
        return new ResponseEntity<>(new FileSystemResource(Z1HTMLFile), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{documentId}/pdf")
    public ResponseEntity<Resource> getAsPDF(@PathVariable String documentId) throws Exception {
        File obrazacZ1PDFFile = z1Service.retrieveZ1AsPDF(documentId);
        if (obrazacZ1PDFFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Z1_" + documentId + ".pdf");
        return new ResponseEntity<>(new FileSystemResource(obrazacZ1PDFFile), headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{documentId}/odobri")
    public ResponseEntity<Void> approveZ1(@PathVariable String documentId, @RequestBody Z1ZavodRequest z1ZavodRequest, @RequestParam String idResenja, @RequestParam String brojPrijave) throws XMLDBException {
        z1Service.setZ1StatusAsOdobren(documentId, z1ZavodRequest, idResenja, brojPrijave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{documentId}/odbij")
    public ResponseEntity<Void> declineZ1(@PathVariable String documentId, @RequestBody Z1ZavodRequest z1ZavodRequest, @RequestParam String idResenja) throws XMLDBException {
        z1Service.setZ1StatusAsOdbijen(documentId, z1ZavodRequest, idResenja);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/metadata/{documentId}/rdf", produces = "application/rdf+xml")
    public ResponseEntity<String> getMetadataAsRDF(@PathVariable String documentId) throws IOException {
        String obrazacZ1RDFMetadata = z1Service.retrieveObrazacZ1MetadataAsRDF(documentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "obrazac_Z-1_" + documentId + "metadata.rdf");
        return new ResponseEntity<>(obrazacZ1RDFMetadata, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/metadata/{documentId}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMetadataAsJSON(@PathVariable String documentId) throws IOException {
        String obrazacZ1JSONMetadata = z1Service.retrieveObrazacZ1MetadataAsJSON(documentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "obrazac_Z-1_" + documentId + "metadata.json");
        return new ResponseEntity<>(obrazacZ1JSONMetadata, headers, HttpStatus.OK);
    }
}
