package com.truck.api.report.service;

import com.truck.api.report.model.DailyReport;
import com.truck.api.transit.TransitTestBuilder;
import com.truck.api.transit.model.Transit;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DailyReportProviderTest {
    private static final String TOTAL_DISTANCE_IN_KM = "12001km";
    private static final String TOTAL_AMOUNT_IN_PLN = "10001PLN";
    private DailyReportProvider provider = new DailyReportProvider();

    @Test
    public void shouldConvertTransits() {
        //assign
        Transit transitWithBigDistance = TransitTestBuilder.builder().withDate(LocalDate.of(2018, 3, 15))
                .withDestAddress("")
                .withSourceAddress("")
                .withDistance(BigDecimal.valueOf(12000))
                .withPrice(BigDecimal.ONE)
                .build();
        Transit transitWithBigPrice = TransitTestBuilder.builder().withDate(LocalDate.of(2018, 3, 15))
                .withDestAddress("")
                .withSourceAddress("")
                .withDistance(BigDecimal.ONE)
                .withPrice(BigDecimal.valueOf(10000))
                .build();

        List<Transit> transits = Lists.newArrayList(transitWithBigDistance, transitWithBigPrice);
        //act
        DailyReport report = provider.provide(transits);
        //assert
        assertThat(report.getDistance().toString()).isEqualToIgnoringCase(TOTAL_DISTANCE_IN_KM);
        assertThat(report.getPrice().toString()).isEqualToIgnoringCase(TOTAL_AMOUNT_IN_PLN);
    }
}