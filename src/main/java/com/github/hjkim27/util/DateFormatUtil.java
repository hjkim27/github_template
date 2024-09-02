package com.github.hjkim27.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author hjkim27
 * @since 24.07.07
 */
@Slf4j
public class DateFormatUtil {

    @Getter
    @AllArgsConstructor
    public enum DateFormat {
        yyyy_MM_dd(new SimpleDateFormat("yyyy-MM-dd")),
        yyyy_MM_dd_hhmmss(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")),
        hhmmss(new SimpleDateFormat("hh:mm:ss"));
        private SimpleDateFormat format;
    }

    public static String getNowDate(DateFormat format) {
        Calendar calendar = Calendar.getInstance();
        String result = format.format.format(calendar.getTime());
        return result;
    }

    public static String getBeforeNDays(DateFormat format, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        String result = format.format.format(calendar.getTime());
        return result;
    }
}
