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
import java.util.Optional;

@Service
public class GoogleTransitService implements TransitService {
    private final GeoApiContext context;

    public GoogleTransitService(GeoApiContext context) {
        this.context = context;
    }

    public void addTransit(Transit transit) {
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey(apiKey).build();
        DistanceMatrixApiRequest distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context,
                new String[]{transit.getSourceAddress()},
                new String[]{transit.getDestAddress()});
        try {
            final DistanceMatrix matrix = distanceMatrix.await();
            Optional<Distance> distance = Optional.ofNullable(matrix.rows[0].elements[0].distance);

            Optional.of(matrix.rows)
                    .map(m -> m[0])
                    .map(e -> e.elements)
                    .map(eleZero -> eleZero[0]);

            String distanceStr = distance.map(c -> c.humanReadable).orElse("0");
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
