package com.truck.api.transit.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
class DistanceMatrixResolver {
    private final Logger logger = Logger.getLogger(DistanceMatrixResolver.class.getName());

    Future<DistanceMatrix> resolveMatrix(final GeoApiContext context, final Transit transit) {
        final DistanceMatrixApiRequest distanceMatrixRequest =
                DistanceMatrixApi.newRequest(context);
        return CompletableFuture.supplyAsync(() -> calculateDistanceMatrix(distanceMatrixRequest, transit));
    }

    private DistanceMatrix calculateDistanceMatrix(DistanceMatrixApiRequest distanceMatrixRequest, Transit transit) {
        DistanceMatrix distanceMatrix = null;
        try {
            distanceMatrix = distanceMatrixRequest
                    .origins(transit.getSourceAddress())
                    .destinations(transit.getDestAddress())
                    .mode(TravelMode.DRIVING)
                    .await();
        } catch (InterruptedException | IOException | ApiException e) {
            logger.log(Level.WARNING, "Could not parse distance request", e);
        }
        return distanceMatrix;
    }
}
