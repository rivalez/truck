package com.truck.api.report.service;

import com.truck.api.generic.DateFormatter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class MonthlyDateFormatter implements DateFormatter {
    private static final String ST = "st";
    private static final String ND = "nd";
    private static final String TH = "th";
    private static final String COMMA_SEPARATOR = ", ";

    @Override
    public String parse(LocalDate date) {
        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String day;
        int dayOfMonth = date.getDayOfMonth();
        switch (dayOfMonth) {
            case 1:
                day = dayOfMonth + ST;
                break;
            case 2:
                day = dayOfMonth + ND;
                break;
            default:
                day = dayOfMonth + TH;
                break;
        }

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        DateTimeFormatter dateTimeFormatter = builder
                .appendLiteral(monthName)
                .appendLiteral(COMMA_SEPARATOR)
                .appendLiteral(day)
                .toFormatter(Locale.ENGLISH);

        return dateTimeFormatter.format(date);
    }
}
