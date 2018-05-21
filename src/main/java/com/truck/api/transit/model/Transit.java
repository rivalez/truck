package com.truck.api.transit.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public final class Transit {
    private String sourceAddress;
    private String destAddress;
    private DateTime date;
    private BigDecimal price;
    private long distance;

    private Transit() {
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public DateTime getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static final class TransitBuilder {
        private String sourceAddress;
        private String destAddress;
        private DateTime date;
        private BigDecimal price;

        private TransitBuilder() {
        }

        public static TransitBuilder builder() {
            return new TransitBuilder();
        }

        public TransitBuilder withSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
            return this;
        }

        public TransitBuilder withDestAddress(String destAddress) {
            this.destAddress = destAddress;
            return this;
        }

        public TransitBuilder withDate(DateTime date) {
            this.date = date;
            return this;
        }

        public TransitBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Transit build() {
            Transit transit = new Transit();
            transit.price = this.price;
            transit.destAddress = this.destAddress;
            transit.sourceAddress = this.sourceAddress;
            transit.date = this.date;
            return transit;
        }
    }
}
