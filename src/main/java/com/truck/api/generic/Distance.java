package com.truck.api.generic;

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

    @Override
    public String toString() {
        return numericDistance + unit;
    }
}
