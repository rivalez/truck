package com.truck.api.transit.service;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

    Page<Transit> getAll(Pageable pageable) {
        return transitRepository.findAll(pageable);
    }
}
