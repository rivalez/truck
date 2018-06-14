package com.truck.api.report.service;

import com.truck.api.transit.TransitTestBuilder;
import com.truck.api.transit.model.Transit;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;


public class MonthlyReportProviderTest {
    private static final String DIFFERENT_MONTHS = "found reports from different months";
    private static final LocalDate MOCK_DATE = LocalDate.of(2018, 3, 3);
    private MonthlyReportProvider provider = new MonthlyReportProvider(
            new MonthlyDateFormatter(), new MonthlyReportCreator(), MOCK_DATE);

    @Test
    public void shouldProvideReports_fromThisMonth() {
        //given
        Transit transit = TransitTestBuilder.builder()
                .withDate(MOCK_DATE)
                .withDistance(BigDecimal.ONE)
                .withSourceAddress("start")
                .withDestAddress("end")
                .withPrice(BigDecimal.ONE)
                .build();
        List<Transit> transits = Lists.newArrayList(transit);
        //when
        final List<MonthlyReport> reports = provider.provide(transits);
        //then
        validReportFromActualMonth(reports);
    }

    private void validReportFromActualMonth(List<MonthlyReport> reports) {
        final String monthName = MOCK_DATE.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        assertThat(reports).isNotEmpty();
        reports.forEach((report) -> assertThat(report.getDate()).as(DIFFERENT_MONTHS).contains(monthName));
    }

    @Test
    public void shouldNotPickReport_fromOtherMonth() {
        //given
        Transit transit = TransitTestBuilder.builder()
                .withDate(LocalDate.of(2018, 4, 4))
                .withDistance(BigDecimal.ONE)
                .withSourceAddress("start")
                .withDestAddress("end")
                .withPrice(BigDecimal.ONE)
                .build();
        List<Transit> transits = Lists.newArrayList(transit);
        //when
        final List<MonthlyReport> reports = provider.provide(transits);
        //then
        assertThat(reports).isEmpty();
    }
}