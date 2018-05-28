package com.truck.api.transit.service;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
        return Stream.of(transitRepository.findAll())
                .map(transit -> transit.iterator().next())
                .collect(toList());
    }
}
