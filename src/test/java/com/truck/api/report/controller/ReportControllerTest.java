package com.truck.api.report.controller;

import com.truck.api.generic.Distance;
import com.truck.api.generic.Price;
import com.truck.api.report.model.DailyReport;
import com.truck.api.report.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    private static final String JSON_IN_VALID_FORMAT = "{total_distance: 1km, total_price: 1PLN}";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    public void shouldAcceptParams() throws Exception {
        String date = LocalDate.now().toString();
        this.mockMvc.perform(get("/reports/daily")
                .param("start_date", date)
                .param("end_date", date))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnDailyReport_InValidFormat() throws Exception {
        LocalDate date = LocalDate.now();
        DailyReport dailyReport = DailyReport.from(
                Distance.from(BigDecimal.ONE, "km"),
                Price.from(BigDecimal.ONE, "PLN")
        );
        when(reportService.createDailyReport(date, date)).thenReturn(dailyReport);
        this.mockMvc.perform(get("/reports/daily")
                .param("start_date", date.toString())
                .param("end_date", date.toString()))
                .andExpect(content().json(JSON_IN_VALID_FORMAT))
                .andExpect(status().isOk());
    }
}