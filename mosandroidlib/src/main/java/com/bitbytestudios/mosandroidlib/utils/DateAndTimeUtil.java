package com.bitbytestudios.mosandroidlib.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 */
public class DateAndTimeUtil {

    public static final String TAG = DateAndTimeUtil.class.getSimpleName();
    
    //static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    //static final String DATEFORMAT = "MM-dd-yyyy HH:mm:ss";
    static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static String convertTimeStampToDate(String yourTimeString) {

        if (yourTimeString != null && !yourTimeString.isEmpty()) {
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(Long.parseLong(yourTimeString) * 1000);
            String date = DateFormat.format("MM/dd/yyyy", cal).toString();
            return date;
        } else {
            return "";
        }
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss formate date as string
     */
    public static String getCurrentTimeStamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String utcTime = dateFormat.format(new Date()); // Find todays date
            return utcTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String convertUTCToTime(String utcString) {

        try {
            //1 / 26 / 2016 3:41:14 PM
            String DATEFORMAT = "MM/dd/yyyy hh:mm:ss aaa";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date myDate = simpleDateFormat.parse(utcString);
            String date = DateFormat.format("hh:mm AAA", myDate).toString();

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
