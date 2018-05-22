package com.truck.api.transit.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class GoogleTransitServiceTest {
    private GeoApiContext context = Mockito.mock(GeoApiContext.class);
    private DistanceMatrixNullHandler distanceMatrixNullHandler = new DistanceMatrixNullHandler();
    private TransitRepository transitRepositorySpy = Mockito.mock(TransitRepository.class);
    private DistanceMatrixResolver distanceMatrixResolver = Mockito.mock(DistanceMatrixResolver.class);

    @Test
    public void shouldAddTransit_toDB() {
        //assign
        DistanceMatrix distanceMatrix = new DistanceMatrix(new String[]{}, new String[]{}, new DistanceMatrixRow[]{});
        when(distanceMatrixResolver.resolveMatrix(any(), isA(Transit.class))).thenReturn(distanceMatrix);
        TransitService transitService = new GoogleTransitService(context,
                distanceMatrixNullHandler,
                transitRepositorySpy,
                distanceMatrixResolver);
        final Transit transit = new Transit();
        //act
        transitService.addTransit(transit);
        //assert
        verify(transitRepositorySpy, times(1)).save(isA(Transit.class));
    }
}