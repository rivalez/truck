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

    public String getAvgDistance() {
        return avgDistance;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public static final class MonthlyReportBuilder {
        private String date;
        private String distance;
        private String avgDistance;
        private String avgPrice;

        private MonthlyReportBuilder() {
        }

        public static MonthlyReportBuilder builder() {
            return new MonthlyReportBuilder();
        }

        public MonthlyReportBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public MonthlyReportBuilder withDistance(String distance) {
            this.distance = distance;
            return this;
        }

        public MonthlyReportBuilder withAvgDistance(String avgDistance) {
            this.avgDistance = avgDistance;
            return this;
        }

        public MonthlyReportBuilder withAvgPrice(String avgPrice) {
            this.avgPrice = avgPrice;
            return this;
        }

        public MonthlyReport build() {
            MonthlyReport monthlyReport = new MonthlyReport();
            monthlyReport.date = this.date;
            monthlyReport.distance = this.distance;
            monthlyReport.avgDistance = this.avgDistance;
            monthlyReport.avgPrice = this.avgPrice;
            return monthlyReport;
        }
    }
}
