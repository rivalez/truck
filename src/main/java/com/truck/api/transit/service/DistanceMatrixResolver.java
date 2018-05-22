package com.truck.api.transit.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
class DistanceMatrixResolver {
    private final Logger logger = Logger.getLogger(DistanceMatrixResolver.class.getName());

    DistanceMatrix resolveMatrix(final GeoApiContext context, final Transit transit) {
        DistanceMatrix distanceMatrix = null;
        final DistanceMatrixApiRequest distanceMatrixRequest =
                DistanceMatrixApi.getDistanceMatrix(
                        context,
                        new String[]{transit.getSourceAddress()},
                        new String[]{transit.getDestAddress()});
        try {
            distanceMatrix = distanceMatrixRequest.await();
        } catch (InterruptedException | IOException | ApiException e) {
            logger.log(Level.WARNING, "Could not parse distance request", e);
        }
        return distanceMatrix;
    }
}
