package com.truck.api.report.service;

import com.truck.api.transit.model.Transit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
class MonthlyReportProvider {
    private final MonthlyDateFormatter monthlyDateFormatter;
    private final MonthlyReportCreator monthlyReportCreator;
    private final LocalDate currentDate;

    @Autowired
    MonthlyReportProvider(MonthlyDateFormatter monthlyDateFormatter, MonthlyReportCreator monthlyReportCreator, LocalDate currentDate) {
        this.monthlyDateFormatter = monthlyDateFormatter;
        this.monthlyReportCreator = monthlyReportCreator;
        this.currentDate = currentDate;
    }

    final List<MonthlyReport> provide(List<Transit> transits) {
        final List<Transit> transitsFromCurrentMonth = filterByDate(transits);
        final Map<String, List<Transit>> transitsByDate =
                transitsFromCurrentMonth.stream()
                        .collect(groupingBy((Transit transit) -> monthlyDateFormatter.parse(transit.getDate())));
        return transitsByDate.entrySet().stream()
                .map(monthlyReportCreator::create)
                .collect(Collectors.toList());
    }

    private List<Transit> filterByDate(List<Transit> transits) {
        return transits.stream().filter(t -> t.getDate().getMonth() == currentDate.getMonth()).collect(Collectors.toList());
    }
}
