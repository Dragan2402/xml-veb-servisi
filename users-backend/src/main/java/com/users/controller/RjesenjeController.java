package com.users.controller;

import com.users.controller.Responses.CreateRjesenjeResponse;
import com.users.controller.Responses.RjesenjeResponse;

import com.users.model.rjesenje.Rjesenje;
import com.users.service.rjesenje.IRjesenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/rjesenje")
@CrossOrigin
public class RjesenjeController {

    @Autowired
    private IRjesenjeService rjesenjeService;

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<CreateRjesenjeResponse> create(@RequestBody Rjesenje rjesenje) throws Exception {
        return new ResponseEntity<>(rjesenjeService.create(rjesenje), HttpStatus.CREATED);
    }

    @GetMapping(value = "getRjesenjePdf")
    public ResponseEntity<Resource> getRjesenjePdf(@RequestParam("requestId") long requestId) throws  Exception{
        ByteArrayResource result = rjesenjeService.getRjesenjeStringByRequestId(requestId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(result.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("rjesenje.pdf")
                                .build().toString())
                .body(result);
    }
}
