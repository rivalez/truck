package com.truck.api.generic;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public class Distance {
    private BigDecimal numericDistance;
    private String unit;

    private Distance() {

    }

    public static Distance from(BigDecimal numericDistance, String unit) {
        Distance dist = new Distance();
        dist.numericDistance = numericDistance;
        dist.unit = unit;
        return dist;
    }

    @JsonValue
    public String getDistance() {
        return numericDistance + unit;
    }

    @Override
    public String toString() {
        return numericDistance + unit;
    }
}
