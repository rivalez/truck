package com.truck.api.transit.service;

import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        //assert
    }
}