package com.crickbuzz.balvier.medialert.controller;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Balvier on 9/17/2017.
 */

public class Util {

    public static long convertStringToDate(String actualDate) {

        SimpleDateFormat convertedDate = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Date date = null;
        try {
            date = sdf.parse(actualDate);

            Log.e("bvc", "milliseconds " + date.getTime());
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(date.getTime());

            int mYear = calendar1.get(Calendar.YEAR);
            int mMonth = calendar1.get(Calendar.MONTH);
            int mDay = calendar1.get(Calendar.DAY_OF_MONTH);
            calendar1.set(Calendar.HOUR_OF_DAY, date.getHours());
            int hr = calendar1.get(Calendar.HOUR_OF_DAY);
            int min = calendar1.get(Calendar.MINUTE);
            Log.e("bvc", "calendar " + mDay + " " + (mMonth + 1) + " " + mYear + " " + hr + " " + min);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("convertedDate.format(date) :" + convertedDate.format(date));
        return date.getTime();

    }

    public static List<String> getTodayDateInRequiredFormat() {
        SimpleDateFormat convertedDate = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Date date = null;
        try {
            date = new Date();

            Log.e("bvc", "milliseconds " + date.getTime());
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(date.getTime());
            int mYear = calendar1.get(Calendar.YEAR);
            int mMonth = calendar1.get(Calendar.MONTH);
            int mDay = calendar1.get(Calendar.DAY_OF_MONTH);
            List<String> stringList = new ArrayList<>();
            stringList.add(new StringBuilder().append(mYear).append("-").append(mMonth).append("-").append(mDay).toString());
            stringList.add(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).toString());
            return stringList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
