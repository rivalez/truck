package com.truck.api.transit.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.truck.api.transit.model.Transit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleTransitService implements TransitService {
    private final GeoApiContext context;
    private final DistanceMatrixNullHandler distanceMatrixNullHandler;
    private final TransitDao transitDao;
    private final DistanceMatrixResolver distanceMatrixResolver;

    @Autowired
    GoogleTransitService(GeoApiContext context,
                         DistanceMatrixNullHandler distanceMatrixNullHandler,
                         TransitDao transitDao,
                         DistanceMatrixResolver distanceMatrixResolver) {
        this.context = context;
        this.distanceMatrixNullHandler = distanceMatrixNullHandler;
        this.transitDao = transitDao;
        this.distanceMatrixResolver = distanceMatrixResolver;
    }

    public void addTransit(Transit transit) {
        final DistanceMatrix matrix = distanceMatrixResolver.resolveMatrix(context, transit);
        final Distance distance = distanceMatrixNullHandler.unpack(matrix);
        transit.setDistance(distance.humanReadable);
        transitDao.trySave(transit);
    }

    @Override
    public List<Transit> getAll() {
        return transitDao.getAll();
    }
}
