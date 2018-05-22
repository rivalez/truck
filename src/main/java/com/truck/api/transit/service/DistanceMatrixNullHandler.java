package com.truck.api.transit.service;

import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DistanceMatrixNullHandler {
    private static final String COULD_NOT_PARSE_DISTANCE = "could not parse distance";

    Distance unpack(DistanceMatrix matrix) {
        final Distance distance = new Distance();
        distance.humanReadable = COULD_NOT_PARSE_DISTANCE;
        return Optional.ofNullable(matrix)
                .filter(ma -> ma.rows == null)
                .filter(ma -> ma.rows.length < 1)
                .map(ma -> ma.rows[0])
                .map(e -> e.elements)
                .map(eleZero -> eleZero[0])
                .map(l -> l.distance)
                .orElse(distance);
    }
}
