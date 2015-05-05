package com.medbrain.fuzzy.medbrain;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Julio on 5/1/15. Prueba1
 */
public class Appointment {
    private Integer ID;
    private String name;
    private String place;
    private String doctorID;
    private Calendar date;

    public Appointment() {
        ID = null;
        name = "";
        place = "";
        doctorID = null;
        date = new GregorianCalendar();
        date.setTime(new Date());

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_YEAR);
        int hour = date.get(Calendar.HOUR);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);
        int ms = date.get(Calendar.MILLISECOND);
        int id = (year + month + day + hour + minute + second + ms);
        this.setID(id);
    }

    public Integer getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getPlace(){
        return place;
    }

    public String getDoctorID(){
        return doctorID;
    }

    public String getStringDate(){
        String finalDate = "";
        Locale usersLocale = Locale.getDefault();
        DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
        String weekdays[] = dfs.getWeekdays();
        int day = date.get(Calendar.DAY_OF_WEEK);
        String nameOfDay = weekdays[day];

        finalDate += nameOfDay + " " + date.get(Calendar.DAY_OF_MONTH) + ", ";

        String months[] = dfs.getMonths();
        int month = date.get(Calendar.MONTH);
        String nameOfMonth = months[month];

        finalDate += nameOfMonth + ", " + date.get(Calendar.YEAR);

        return finalDate;
    }

    public Calendar getDate(){
        return date;
    }

    public void setID(Integer ID){
        this.ID = ID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public void setInnerCalendar(int timeInMills){
        date.setTimeInMillis(timeInMills);
    }

}