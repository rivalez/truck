package com.truck.api.transit.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleTransitService implements TransitService {
    private final GeoApiContext context;
    private DistanceMatrixNullHandler distanceMatrixNullHandler;

    public GoogleTransitService(GeoApiContext context, DistanceMatrixNullHandler distanceMatrixNullHandler) {
        this.context = context;
        this.distanceMatrixNullHandler = distanceMatrixNullHandler;
    }

    public void addTransit(Transit transit) {
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey(apiKey).build();
        DistanceMatrixApiRequest distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context,
                new String[]{transit.getSourceAddress()},
                new String[]{transit.getDestAddress()});
        try {
            final DistanceMatrix matrix = distanceMatrix.await();
            Distance distance = distanceMatrixNullHandler.unpack(matrix);
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
