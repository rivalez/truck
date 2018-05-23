package com.truck.api.transit.service;

import com.truck.api.generic.Validator;
import com.truck.api.transit.model.Transit;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransitValidatorTest {
    private Validator<Transit> validator = new TransitValidator();

    @Test
    public void shouldReturnFalse_whenTransitIsEmpty() {
        //assign
        final Transit transit = new Transit();
        //act
        boolean result = validator.validate(transit);
        //assert
        assertFalse("Transit is empty should not be valid!", result);
    }

    @Test
    public void shouldReturnFalse_whenTransitDistanceIsMissing() {
        //assign
        final Transit transit = new Transit();
        transit.setPrice(BigDecimal.ONE);
        transit.setSourceAddress("");
        transit.setDestAddress("");
        transit.setDate(Date.valueOf(LocalDate.now()));
        //act
        boolean result = validator.validate(transit);
        //assert
        assertFalse("Transit is missing distance should not be valid!", result);
    }

    @Test
    public void shouldReturnTrue_whenTransitIsComplete() {
        //assign
        final Transit transit = new Transit();
        transit.setDate(Date.valueOf(LocalDate.now()));
        transit.setDistance("0 km");
        transit.setPrice(BigDecimal.ONE);
        transit.setSourceAddress("");
        transit.setDestAddress("");
        //act
        boolean result = validator.validate(transit);
        //assert
        assertTrue("Transit is complete should be valid!", result);
    }
}