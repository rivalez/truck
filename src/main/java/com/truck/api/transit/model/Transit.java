package com.truck.api.transit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Transit {

    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("source_address")
    private String sourceAddress;
    @JsonProperty("destination_address")
    private String destAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private BigDecimal price;
    private String distance;

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
