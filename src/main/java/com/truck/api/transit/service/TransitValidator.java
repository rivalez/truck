package com.truck.api.transit.service;

import com.truck.api.generic.Validator;
import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
class TransitValidator implements Validator<Transit> {
    @Override
    public boolean validate(Transit value) {
        return Stream.of(value.getDistance(),
                value.getDate(),
                value.getPrice()).allMatch(Objects::nonNull);
    }
}
