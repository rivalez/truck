package com.truck.api.transit.service;

import com.truck.api.transit.model.Transit;

import java.util.List;

public interface TransitService {
    void addTransit(Transit transit);
    List<Transit> getAll();
}
