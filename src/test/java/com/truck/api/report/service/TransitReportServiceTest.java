package com.truck.api.report.service;

import com.truck.api.report.model.DailyReport;
import com.truck.api.transit.TransitTestBuilder;
import com.truck.api.transit.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransitReportServiceTest {
    private static final LocalDate FROM = LocalDate.of(2018, 3, 15);
    private static final LocalDate TILL = LocalDate.of(2018, 3, 20);
    @Autowired
    private DailyReportProvider dailyReportProvider;
    @Autowired
    private TransitRepository transitRepository;
    private ReportService reportService;

    @Before
    public void setUp() {
        //given
        transitRepository.saveAll(Stream.generate(() -> TransitTestBuilder.builder().withDate(FROM)
                .withDestAddress("")
                .withSourceAddress("")
                .withDistance(BigDecimal.valueOf(200))
                .withPrice(BigDecimal.valueOf(200))
                .build()).limit(5).collect(Collectors.toList()));
        reportService = new TransitReportService(transitRepository, dailyReportProvider);
    }

    @Test
    public void shouldReturnFullReportService() {
        //when
        DailyReport dailyReport = reportService.createDailyReport(FROM, TILL);

        //then
        assertThat(dailyReport.getDistance().toString()).isEqualToIgnoringCase("1000.00km");
        assertThat(dailyReport.getPrice().toString()).isEqualToIgnoringCase("1000.00PLN");
    }
}