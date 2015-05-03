package com.medbrain.fuzzy.medbrain;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Julio on 5/1/15.
 */
public class Appointment {
    private String name;
    private String place;
    private int doctorID;
    private Calendar innerDate;

    public String getName(){
        return name;
    }

    public String getPlace(){
        return place;
    }

    public int getDoctorID(){
        return doctorID;
    }

    public String getStringDate(){
        String finalDate = "";
        Locale usersLocale = Locale.getDefault();
        DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
        String weekdays[] = dfs.getWeekdays();
        int day = innerDate.get(Calendar.DAY_OF_WEEK);
        String nameOfDay = weekdays[day];
        finalDate += nameOfDay + " " + innerDate.get(Calendar.DAY_OF_MONTH) + ", ";
        String months[] = dfs.getMonths();
        int month = innerDate.get(Calendar.MONTH);
        String nameOfMonth = months[month];
        finalDate += nameOfMonth + ", " + innerDate.get(Calendar.YEAR);
        return finalDate;
    }
}




