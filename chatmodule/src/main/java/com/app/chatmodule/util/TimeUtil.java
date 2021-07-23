package com.app.chatmodule.util;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;

public class TimeUtil {
    public static String getFormattedDate(long smsTimeInMilis) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(smsTimeInMilis);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "EEEE, MMMM d";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return "Today";
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
            return "Yesterday";
        } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
            return DateFormat.format(dateTimeFormatString, smsTime).toString();
        } else {
            return DateFormat.format("MMMM dd yyyy", smsTime).toString();
        }
    }

    public static String getTimeFromTimeStamp(long timestamp) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(timestamp);
        return DateFormat.format("h:mm aa", smsTime).toString();
    }
}
