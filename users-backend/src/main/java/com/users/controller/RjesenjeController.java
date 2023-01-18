package com.users.controller;

import com.users.controller.Responses.SifraResponse;
import com.users.model.rjesenje.Rjesenje;
import com.users.service.rjesenje.IRjesenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/rjesenje")
@CrossOrigin
public class RjesenjeController {

    @Autowired
    private IRjesenjeService rjesenjeService;

    @PostMapping(produces = {"application/xml"})
    public ResponseEntity<SifraResponse> create(@RequestBody Rjesenje rjesenje) throws Exception {
        return new ResponseEntity<>(new SifraResponse(rjesenjeService.create(rjesenje).getSifra().toString()), HttpStatus.CREATED);
    }
}
