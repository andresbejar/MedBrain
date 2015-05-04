package com.medbrain.fuzzy.medbrain;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Julio on 5/2/15.
 */
public class Reminder {
    private String name;
    private Calendar innerDate;

    public Reminder(){
        name = "";
        innerDate = null;
    }

    public String getName(){
        return name;
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

    public Calendar getInnerDate(){
        return innerDate;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setInnerCalendar(Calendar innerCalendar){
        this.innerDate = innerCalendar;
    }

}