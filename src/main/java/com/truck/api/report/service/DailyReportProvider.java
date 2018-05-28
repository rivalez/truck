package com.truck.api.report.service;

import com.truck.api.generic.Distance;
import com.truck.api.generic.Price;
import com.truck.api.report.model.DailyReport;
import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Component
class DailyReportProvider {
    DailyReport provide(List<Transit> foundTransits) {
        BigDecimal distance = getValues(foundTransits, Transit::getDistance);
        BigDecimal price = getValues(foundTransits, Transit::getPrice);
        return DailyReport.from(
                Distance.from(distance, "km"),
                Price.from(price, "PLN"));
    }

    private BigDecimal getValues(List<Transit> foundTransits, Function<Transit, BigDecimal> function) {
        return foundTransits.stream()
                .map(function)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
