package com.truck.api.report.service;

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

    @Autowired
    TransitReportService(TransitRepository transitRepository, DailyReportProvider dailyReportProvider) {
        this.transitRepository = transitRepository;
        this.dailyReportProvider = dailyReportProvider;
    }

    @Override
    public DailyReport createDailyReport(LocalDate from, LocalDate till) {
        List<Transit> foundTransits = transitRepository.findByDateBetween(from, till);
        return dailyReportProvider.provide(foundTransits);
    }
}
