package com.truck.api.report.controller;

import com.truck.api.report.model.DailyReport;
import com.truck.api.report.service.MonthlyReport;
import com.truck.api.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/reports/daily", params = {"start_date", "end_date"}, method = RequestMethod.GET)
    public ResponseEntity<DailyReport> getDailyReport(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("start_date") LocalDate from,
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("end_date") LocalDate till) {
        return new ResponseEntity<>(reportService.createDailyReport(from, till), HttpStatus.OK);
    }

    @RequestMapping("/reports/monthly")
    public ResponseEntity<List<MonthlyReport>> getMonthlyReport() {
        return new ResponseEntity<>(reportService.createMonthlyReport(), HttpStatus.OK);
    }
}
