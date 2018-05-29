package com.truck.api.report.service;

import com.truck.api.transit.TransitTestBuilder;
import com.truck.api.transit.model.Transit;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MonthlyReportCreatorTest {
    private MonthlyReportCreator monthlyReportCreator = new MonthlyReportCreator();

    @Test
    public void shouldCreateMonthlyReport() {
        //assign
        Transit transit1 = TransitTestBuilder.builder().withDate(LocalDate.of(2018, 3, 15))
                .withDestAddress("")
                .withSourceAddress("")
                .withDistance(BigDecimal.valueOf(500))
                .withPrice(BigDecimal.valueOf(500))
                .build();
        Transit transit2 = TransitTestBuilder.builder().withDate(LocalDate.of(2018, 3, 15))
                .withDestAddress("")
                .withSourceAddress("")
                .withDistance(BigDecimal.valueOf(750))
                .withPrice(BigDecimal.valueOf(750))
                .build();
        Map.Entry<String, List<Transit>> transits = new AbstractMap.SimpleEntry<>("any", Lists.newArrayList(transit1, transit2));
        //act
        final MonthlyReport monthlyReport = monthlyReportCreator.create(transits);
        //assert
        assertThat(monthlyReport.getDistance()).contains("1250");
        assertThat(monthlyReport.getAvgDistance()).contains("625");
        assertThat(monthlyReport.getAvgPrice()).contains("625");
    }
}