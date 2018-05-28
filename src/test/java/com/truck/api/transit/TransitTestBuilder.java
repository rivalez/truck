package com.truck.api.transit;

import com.truck.api.transit.model.Transit;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransitTestBuilder {
    private String sourceAddress;
    private String destAddress;
    private LocalDate date;
    private BigDecimal price;
    private BigDecimal distance;

    private TransitTestBuilder() {
    }

    public static TransitTestBuilder builder() {
        return new TransitTestBuilder();
    }

    public TransitTestBuilder withSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
        return this;
    }

    public TransitTestBuilder withDestAddress(String destAddress) {
        this.destAddress = destAddress;
        return this;
    }

    public TransitTestBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public TransitTestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransitTestBuilder withDistance(BigDecimal distance) {
        this.distance = distance;
        return this;
    }

    public Transit build() {
        Transit transit = new Transit();
        transit.setSourceAddress(sourceAddress);
        transit.setDestAddress(destAddress);
        transit.setDate(date);
        transit.setPrice(price);
        transit.setDistance(distance);
        return transit;
    }
}
