package com.truck.api.report.service;

import com.truck.api.transit.model.Transit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class MonthlyReportProvider {
    private MonthlyDateFormatter monthlyDateFormatter;
    private MonthlyReportCreator monthlyReportCreator;

    @Autowired
    public MonthlyReportProvider(MonthlyDateFormatter monthlyDateFormatter, MonthlyReportCreator monthlyReportCreator) {
        this.monthlyDateFormatter = monthlyDateFormatter;
        this.monthlyReportCreator = monthlyReportCreator;
    }

    final List<MonthlyReport> provide(List<Transit> transits) {
        final Map<String, List<Transit>> transitsByDate =
                transits.stream()
                        .collect(groupingBy((Transit transit) -> monthlyDateFormatter.parse(transit.getDate())));
        return transitsByDate.entrySet().stream()
                .map(monthlyReportCreator::create)
                .collect(Collectors.toList());
    }
}
