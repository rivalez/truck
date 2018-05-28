package com.truck.api.report.service;

import com.truck.api.generic.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthlyDateFormatter implements DateFormatter {
    @Override
    public String parse(LocalDate date) {
        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String day;
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth == 1) {
            day = dayOfMonth + "st";
        } else if (dayOfMonth == 2) {
            day = dayOfMonth + "nd";
        } else {
            day = dayOfMonth + "th";
        }

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        DateTimeFormatter dateTimeFormatter = builder
                .appendLiteral(monthName)
                .appendLiteral(", ")
                .appendLiteral(day)
                .toFormatter(Locale.ENGLISH);

        return dateTimeFormatter.format(date);
    }
}
