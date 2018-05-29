package com.truck.api.report.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyReport {
    private String date;
    @JsonProperty(value = "total_distance")
    private String distance;
    @JsonProperty(value = "avg_distance")
    private String avgDistance;
    @JsonProperty(value = "avg_price")
    private String avgPrice;

    public String getDate() {
        return date;
    }

    public String getDistance() {
        return distance;
    }

    String getAvgDistance() {
        return avgDistance;
    }

    String getAvgPrice() {
        return avgPrice;
    }

    static final class MonthlyReportBuilder {
        private String date;
        private String distance;
        private String avgDistance;
        private String avgPrice;

        private MonthlyReportBuilder() {
        }

        static MonthlyReportBuilder builder() {
            return new MonthlyReportBuilder();
        }

        MonthlyReportBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        MonthlyReportBuilder withDistance(String distance) {
            this.distance = distance;
            return this;
        }

        MonthlyReportBuilder withAvgDistance(String avgDistance) {
            this.avgDistance = avgDistance;
            return this;
        }

        MonthlyReportBuilder withAvgPrice(String avgPrice) {
            this.avgPrice = avgPrice;
            return this;
        }

        MonthlyReport build() {
            MonthlyReport monthlyReport = new MonthlyReport();
            monthlyReport.date = this.date;
            monthlyReport.distance = this.distance;
            monthlyReport.avgDistance = this.avgDistance;
            monthlyReport.avgPrice = this.avgPrice;
            return monthlyReport;
        }
    }
}
