package com.truck.api.report.service;

import com.truck.api.report.model.DailyReport;

import java.time.LocalDate;

public interface ReportService {
    DailyReport createDailyReport(LocalDate from, LocalDate till);
}
