package com.duarte.victor.plr.util;

import android.text.format.DateFormat;

import java.util.Date;

public class DateUtil {

    public  static String formatTime(Date date) {

        Date now = new Date();

        long different = now.getTime() - date.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;


        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        if (elapsedDays > 0) {
            if (elapsedDays < 5)
                return Long.toString(elapsedDays, 0) + "d";
            else
                return DateFormat.format("dd/MM/yyyy", date).toString();
        }

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        if (elapsedHours > 0)
            return Long.toString(elapsedHours, 0) + "h";

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        if (elapsedMinutes > 0)
            return Long.toString(elapsedMinutes, 0) + "m";

        long elapsedSeconds = different / secondsInMilli;

        if (elapsedSeconds > 0)
            return Long.toString(elapsedSeconds, 0) + "s";

        return "now";
    }
}
