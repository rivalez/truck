package com.truck.api.transit.service;

import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;

import java.util.Optional;

class DistanceMatrixNullHandler {
    Distance unpack(DistanceMatrix matrix) {
        return Optional.ofNullable(matrix)
                .map(ma -> Optional.ofNullable(ma.rows).orElse(null))
                .map(row -> row[0])
                .map(e -> e.elements)
                .map(eleZero -> eleZero[0])
                .map(l -> l.distance)
                .orElse(new Distance());
    }
}
