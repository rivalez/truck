package com.truck.api.transit.service;

import com.truck.api.transit.model.Transit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransitService {
    void addTransit(Transit transit);

    Page<Transit> getAll(Pageable pageable);
}
