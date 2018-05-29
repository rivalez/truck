package com.truck.api.report.service;

import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.summarizingDouble;

@Component
class MonthlyReportCreator {
    MonthlyReport create(Map.Entry<String, List<Transit>> transits) {
        final DoubleSummaryStatistics distanceStatistics = transits.getValue().stream().collect(summarizingDouble((Transit tran) -> tran.getDistance().doubleValue()));
        final String priceAverage = String.valueOf(transits.getValue().stream().collect(summarizingDouble((Transit tran) -> tran.getPrice().doubleValue())).getAverage());
        return MonthlyReport.MonthlyReportBuilder.builder()
                .withDate(transits.getKey())
                .withAvgPrice(priceAverage)
                .withAvgDistance(String.valueOf(distanceStatistics.getAverage()))
                .withDistance(String.valueOf(distanceStatistics.getSum()))
                .build();
    }
}
