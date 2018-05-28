package com.truck.api.transit.service;

import com.truck.api.transit.TransitTestBuilder;
import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransitDaoTest {
    @Mock
    private TransitRepository transitRepository;
    private TransitValidator transitValidator = new TransitValidator();
    private TransitDao transitDao;

    @Before
    public void setUp() {
        this.transitDao = new TransitDao(transitRepository, transitValidator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSave_emptyTransit() {
        //assign
        final Transit transit = new Transit();
        //act
        transitDao.trySave(transit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSave_transitWithoutPrice() {
        //assign
        final Transit transit = TransitTestBuilder.builder()
                .withDistance(BigDecimal.ONE)
                .withDate(LocalDate.now())
                .build();
        //act
        transitDao.trySave(transit);
    }

    @Test
    public void shouldSave_validTransit() {
        //assign
        final Transit transit = TransitTestBuilder.builder()
                .withDistance(BigDecimal.ONE)
                .withPrice(BigDecimal.ONE)
                .withDate(LocalDate.now())
                .build();
        //act
        transitDao.trySave(transit);
        //assert
        verify(transitRepository, times(1)).save(isA(Transit.class));
    }
}