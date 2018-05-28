package com.truck.api.transit.controller;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransitController {
    private final TransitService transitService;

    @Autowired
    public TransitController(TransitService transitService) {
        this.transitService = transitService;
    }

    @RequestMapping(path = "/transits", method = RequestMethod.POST)
    public ResponseEntity addTransit(@RequestBody Transit transit) {
        transitService.addTransit(transit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
