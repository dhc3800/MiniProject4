package com.dhc3800.mp4;

import java.util.GregorianCalendar;


public class Utils {

    public static String dateConvert(int year, int month, int day) {
        return Integer.toString(month + 1) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
    }

    public static String dateAdd0(int year, int month, int day) {
        String lel;
        if (month < 10) {
            if (day < 10) {
                lel = "0" + month +"/0"+day +  "/" + year;
            } else {
                lel = "0" + month+"/" +day + "/" + year;
            }
        } else {
            if (day < 10) {
                lel = month + "/" + "0" + day + "/" + year;
            } else {
                lel = month+"/" + day + "/" + year;
            }
        }
        return lel;

    }

    public static long time(String date) {
        int month = 0;
        int day = 0;
        int year = 0;
        for (int i = 0; i < 10; i++) {
            if (i < 2) {
                month = month * 10 + date.charAt(i);
            } else if (i < 5 && i >= 3) {
                day = day * 10 + date.charAt(i);
            } else if (i >= 6) {
                year = year * 10 + date.charAt(i);
            }
        }
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        return gc.getTimeInMillis();
    }
}

