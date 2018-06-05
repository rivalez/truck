package com.truck.api.transit.controller;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
        HttpHeaders httpHeaders = new HttpHeaders();
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transit.getId())
                .toUri();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(transit, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transits", method = RequestMethod.GET)
    public ResponseEntity getTransits(Pageable pageable) {
        return new ResponseEntity<>(transitService.getAll(pageable), HttpStatus.OK);
    }
}
