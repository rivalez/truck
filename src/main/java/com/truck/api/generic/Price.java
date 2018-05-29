package com.truck.api.generic;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public class Price {
    private BigDecimal numericPrice;
    private String currency;

    private Price() {
    }

    public static Price from(BigDecimal numericPrice, String currency) {
        Price price = new Price();
        price.numericPrice = numericPrice;
        price.currency = currency;
        return price;
    }

    @JsonValue
    public String getPrice() {
        return numericPrice + currency;
    }

    @Override
    public String toString() {
        return numericPrice + currency;
    }
}
