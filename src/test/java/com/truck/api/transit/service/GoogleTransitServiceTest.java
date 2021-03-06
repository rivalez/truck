package com.truck.api.transit.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class GoogleTransitServiceTest {
    private DistanceMatrixNullHandler distanceMatrixNullHandler = mock(DistanceMatrixNullHandler.class);
    private DistanceMatrixResolver distanceMatrixResolverStub = mock(DistanceMatrixResolver.class);
    private TransitRepository transitRepositorySpy = mock(TransitRepository.class);
    private TransitValidator transitValidator = mock(TransitValidator.class);
    private GeoApiContext context = mock(GeoApiContext.class);
    private final TransitDao transitDao = new TransitDao(transitRepositorySpy, transitValidator);
    private final GoogleTransitService sut = new GoogleTransitService(context, distanceMatrixNullHandler, transitDao, distanceMatrixResolverStub);

    @Before
    public void setUp() {
        Distance distance = new Distance();
        distance.humanReadable = "";
        DistanceMatrix distanceMatrix = new DistanceMatrix(new String[]{}, new String[]{}, new DistanceMatrixRow[]{});
        Future<DistanceMatrix> distanceMatrixF = CompletableFuture.completedFuture(distanceMatrix);
        when(distanceMatrixResolverStub.resolveMatrix(any(), isA(Transit.class))).thenReturn(distanceMatrixF);
        when(distanceMatrixNullHandler.unpack(any())).thenReturn(distance);
    }

    @Test
    public void shouldAddTransit_toDB() {
        //assign
        final Transit transit = new Transit();
        when(transitValidator.validate(isA(Transit.class))).thenReturn(Boolean.TRUE);
        //act
        sut.addTransit(transit);
        //assert
        verify(transitRepositorySpy, times(1)).save(isA(Transit.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAdd_emptyTransit() {
        //given
        final Transit transit = new Transit();
        //when
        sut.addTransit(transit);
    }
}