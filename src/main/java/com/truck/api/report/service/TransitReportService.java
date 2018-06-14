package com.truck.api.report.service;

import com.google.common.collect.Lists;
import com.truck.api.report.model.DailyReport;
import com.truck.api.transit.model.Transit;
import com.truck.api.transit.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransitReportService implements ReportService {
    private TransitRepository transitRepository;
    private DailyReportProvider dailyReportProvider;
    private MonthlyReportProvider monthlyReportProvider;

    @Autowired
    TransitReportService(TransitRepository transitRepository, DailyReportProvider dailyReportProvider, MonthlyReportProvider monthlyReportProvider) {
        this.transitRepository = transitRepository;
        this.dailyReportProvider = dailyReportProvider;
        this.monthlyReportProvider = monthlyReportProvider;
    }

    @Override
    public DailyReport createDailyReport(LocalDate from, LocalDate till) {
        List<Transit> foundTransits = transitRepository.findByDateBetween(from, till);
        return dailyReportProvider.provide(foundTransits);
    }

    @Override
    public List<MonthlyReport> createMonthlyReport() {
        return monthlyReportProvider.provide(Lists.newArrayList(transitRepository.findAll()));
    }
}
