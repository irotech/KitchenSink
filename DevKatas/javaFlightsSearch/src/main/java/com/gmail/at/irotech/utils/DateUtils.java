package com.gmail.at.irotech.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static DateTimeFormatter inputSimpleDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter simpleDateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public static DateTimeFormatter atTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    public static Date stringInputDateToDate(String inputDate) {
        return Date.from(LocalDate.parse(inputDate, inputSimpleDateFormatter).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date stringDateToDate(Integer day, Integer month, Integer year) {
        return Date.from(stringDateToLocalDate(String.format("%02d", day), String.format("%02d", month), String.format("%02d", year)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate stringDateToLocalDate(String day, String month, String year) {
        final StringBuilder sb = new StringBuilder().append(month).append("-").append(day).append("-").append(year);
        return LocalDate.parse(sb.toString(), simpleDateFormatter);
    }

    public static String dateToString(Date inputDate) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(inputDate.toInstant(), ZoneId.systemDefault());
        return localDateTime.format(atTimeFormatter);
    }

    public LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
