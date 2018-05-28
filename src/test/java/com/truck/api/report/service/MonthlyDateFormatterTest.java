package com.truck.api.report.service;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class MonthlyDateFormatterTest {
    private MonthlyDateFormatter formatter = new MonthlyDateFormatter();

    @Test
    public void shouldParseFirstDayOfMonth() {
        //assign
        LocalDate first = LocalDate.of(2018, 3, 1);
        //act
        String result = formatter.parse(first);
        //assert
        assertThat(result).isEqualTo("March, 1st");
    }

    @Test
    public void shouldParseSecondDayOfMonth() {
        //assign
        LocalDate first = LocalDate.of(2018, 3, 2);
        //act
        String result = formatter.parse(first);
        //assert
        assertThat(result).isEqualTo("March, 2nd");
    }

    @Test
    public void shouldParseThirdDayOfMonth() {
        //assign
        LocalDate first = LocalDate.of(2018, 3, 3);
        //act
        String result = formatter.parse(first);
        //assert
        assertThat(result).isEqualTo("March, 3th");
    }

    @Test
    public void shouldParseLastDayOfMonth() {
        //assign
        LocalDate first = LocalDate.of(2018, 3, 31);
        //act
        String result = formatter.parse(first);
        //assert
        assertThat(result).isEqualTo("March, 31th");
    }
}