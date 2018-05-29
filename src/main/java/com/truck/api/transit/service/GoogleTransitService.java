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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GoogleTransitService implements TransitService {
    private final GeoApiContext context;
    private final DistanceMatrixNullHandler distanceMatrixNullHandler;
    private final TransitDao transitDao;
    private final DistanceMatrixResolver distanceMatrixResolver;
    private static final Logger logger = Logger.getLogger(GoogleTransitService.class.getName());

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
            transit.setDistance(BigDecimal.valueOf(convertToKMs(distance)));
        } catch (InterruptedException | ExecutionException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        transitDao.trySave(transit);
    }

    private long convertToKMs(Distance distance) {
        return distance.inMeters / 1000;
    }

    @Override
    public List<Transit> getAll() {
        return transitDao.getAll();
    }
}
