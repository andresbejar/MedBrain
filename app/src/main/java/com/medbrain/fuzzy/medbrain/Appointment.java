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

    /**
     * Constructor de la clase Cita, asigna un entero como identificador que es formado por todos los numeros posibles de la fecha actual
     * donde fue creado el objeto
     * @param
     * @return void
     */
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

    /**
     * Devuelve en ID de la cita como un integer formado por la fecha de la cita.
     * @param null
     * @return Integer
     */
    public Integer getID(){
        return ID;
    }
    /**
     * Devuelve el nombre de la cita como un string
     * @param null
     * @return String
     */
    public String getName(){
        return name;
    }

    /**
     * Devuelve el lugar de la cita como un string
     * @param null
     * @return String
     */
    public String getPlace(){
        return place;
    }

    /**
     * Devuelve un ID al doctor de la cita
     * @param null
     * @return String
     */
    public String getDoctorID(){
        return doctorID;
    }

    /**
     * Formatea la fecha en un string facil de leer con nombres de los dias de la semana
     * @param null
     * @return String
     */
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

    /**
     * Devuelve un objeto de tipo Calendar para poder deducir la fecha de la cita.
     * @param null
     * @return Calendar
     */
    public Calendar getDate(){
        return date;
    }

    /**
     * Fija el ID de la cita que se maneja solo internamente
     * @param int ID
     * @return void
     */
    public void setID(Integer ID){
        this.ID = ID;
    }

    /**
     * Fija el nombre de la cita
     * @param string name
     * @return void
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Fija el lugar de la cita
     * @param string place
     * @return void
     */
    public void setPlace(String place){
        this.place = place;
    }

    /**
     * Fija el ID del doctor correspondiente a la cita
     * @param string correspondiente al doctor de la clase Contact
     * @return void
     */
    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }
    /**
     * Fija la fecha interna del objeto con el tiempo en milisegundos
     * @param int timeInMills
     * @return void
     */
    public void setInnerCalendar(long timeInMills){
        date.setTimeInMillis(timeInMills);
    }

}