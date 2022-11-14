package com.example.demo.Validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataNascimento {
    public static boolean isDateFuture(final String date, final String dateFormat) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate inputDate = LocalDate.parse(date, dtf);

        System.out.println(inputDate.isAfter(localDate));
        return inputDate.isAfter(localDate);
    }
}
