package com.truck.api.report.service;

import com.truck.api.report.model.DailyReport;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    DailyReport createDailyReport(LocalDate from, LocalDate till);

    List<MonthlyReport> createMonthlyReport();
}
