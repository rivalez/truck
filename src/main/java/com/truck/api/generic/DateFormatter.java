package com.truck.api.generic;

import java.time.LocalDate;

public interface DateFormatter {
    String parse(LocalDate date);
}
