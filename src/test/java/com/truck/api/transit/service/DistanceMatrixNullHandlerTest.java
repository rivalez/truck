package com.truck.api.transit.service;

import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DistanceMatrixNullHandlerTest {

    @Test
    public void shouldReturn_DistanceWith0() {
        //assign
        DistanceMatrixNullHandler handler = new DistanceMatrixNullHandler();
        DistanceMatrixRow[] dummy = new DistanceMatrixRow[]{};
        //act
        Distance distance = handler.unpack(new DistanceMatrix(new String[]{}, new String[]{}, dummy));
        //assert
        assertThat(distance.inMeters).isEqualTo(0);
    }
}