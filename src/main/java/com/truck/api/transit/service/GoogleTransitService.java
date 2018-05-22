package com.truck.api.transit.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleTransitService implements TransitService {
    private final GeoApiContext context;
    private final DistanceMatrixNullHandler distanceMatrixNullHandler;
    private final TransitRepository transitRepository;
    private final DistanceMatrixResolver distanceMatrixResolver;


    GoogleTransitService(GeoApiContext context,
                         @Autowired DistanceMatrixNullHandler distanceMatrixNullHandler,
                         @Autowired TransitRepository transitRepository,
                         @Autowired DistanceMatrixResolver distanceMatrixResolver) {
        this.context = context;
        this.distanceMatrixNullHandler = distanceMatrixNullHandler;
        this.transitRepository = transitRepository;
        this.distanceMatrixResolver = distanceMatrixResolver;
    }

    public void addTransit(Transit transit) {
        final DistanceMatrix matrix = distanceMatrixResolver.resolveMatrix(context, transit);
        final Distance distance = distanceMatrixNullHandler.unpack(matrix);
        transit.setDistance(distance.humanReadable);
        transitRepository.save(transit);
    }
}
