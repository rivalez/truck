package com.truck.api.report.service;

import com.truck.api.transit.model.Transit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
public class MonthlyReportProvider {
    List<MonthlyReport> provide(List<Transit> transits) {
        Map<LocalDate, List<Transit>> byDate = transits.stream()
                .collect(groupingBy(Transit::getDate));
        return null;
    }
}
