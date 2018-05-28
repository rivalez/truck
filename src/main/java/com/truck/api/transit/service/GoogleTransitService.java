package com.truck.api.transit.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.truck.api.transit.model.Transit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        final Future<DistanceMatrix> matrix = distanceMatrixResolver.resolveMatrix(context, transit);
        try {
            final Distance distance = distanceMatrixNullHandler.unpack(matrix.get());
            transit.setDistance(BigDecimal.valueOf(distance.inMeters));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        transitDao.trySave(transit);
    }

    @Override
    public List<Transit> getAll() {
        return transitDao.getAll();
    }
}
