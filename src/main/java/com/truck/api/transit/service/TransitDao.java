package com.truck.api.transit.service;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class TransitDao {
    private final TransitRepository transitRepository;
    private final TransitValidator transitValidator;

    TransitDao(TransitRepository transitRepository, TransitValidator transitValidator) {
        this.transitRepository = transitRepository;
        this.transitValidator = transitValidator;
    }

    void trySave(Transit transit) {
        if (transitValidator.validate(transit)) {
            transitRepository.save(transit);
        } else {
            throw new IllegalArgumentException("Invalid Transit! Won't be save to DB");
        }
    }

    List<Transit> getAll() {
        return transitRepository.findAll();
    }
}
