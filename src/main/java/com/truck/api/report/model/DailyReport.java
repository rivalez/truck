package com.truck.api.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.truck.api.generic.Distance;
import com.truck.api.generic.Price;

public class DailyReport {
    @JsonProperty("total_distance")
    private Distance distance;
    @JsonProperty("total_price")
    private Price price;

    public static DailyReport from(Distance distance, Price price) {
        DailyReport dailyReport = new DailyReport();
        dailyReport.distance = distance;
        dailyReport.price = price;
        return dailyReport;
    }

    public Distance getDistance() {
        return distance;
    }

    public Price getPrice() {
        return price;
    }
}
