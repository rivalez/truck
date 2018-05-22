package com.truck.api.transit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransitController {

    @RequestMapping(path = "/transits", method = RequestMethod.POST)
    public ResponseEntity addTransit() {
        final ResponseEntity response = new ResponseEntity(HttpStatus.CREATED);
        return response;
    }
}
