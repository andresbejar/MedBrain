package com.medbrain.fuzzy.medbrain;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase Medicina, almacena y maneja toda la informacion de una medicina en particular
 * @author Andres Bejarano
 */
public class Medicine implements Parcelable {
    private String name;
    private int id;
    private String dose;
    private String details;

    /**
     * Constructor
     * @param name nombre de la medicina
     */
    public Medicine(String name){
        this.name = name;
    }

    /**
     * Constructor default
     * @param _id ID de la medicina
     */
    public Medicine(int _id){id = _id;}

    /**
     * Retorna el nombre de la medicina
     * @return String nombre de la medicina
     */
    public String getName(){
        return name;
    }

    /**
     * Retorna el id de la medicina
     * @return int id de la medicina
     */
    public int getID(){
        return id;
    }

    /**
     * Retorna la dosis de la medicina
     * @return String dosis de la medicina
     */
    public String getDose(){
        return dose;
    }

    /**
     * Especifica la dosis de la medicina
     * @param dose String dosis especificada
     */
    public void setDose(String dose) {
        this.dose = dose;
    }

    /**
     * Especifica la id de la medicina
     * @param ID especificada
     */
    public void setID(int ID) {
        this.id = ID;
    }

    /**
     * Inicializa una nueva medicina, cuya informacion aun no existe en la BD
     * Crea un nuevo id de la medicina basado en la hora de creacion del objeto
     */
    public void initializeNewMedicine(){
        Calendar calendar = new GregorianCalendar();

        int year = calendar.get(Calendar.YEAR);
        // Mae, si ya usa day of year (1-365), podria deshacerse del month.
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);

        int id = (year + month + day + hour + minute + second + ms);
        this.setID(id);
    }

    /**
     * Especifica detalles de la medicina
     * @param _details String detalles especificados
     */
    public void setDetails(String _details){
        details = _details;
    }

    /**
     * Retorna los detalles de la medicina
     * @return String detalles de la medicina
     */
    public String getDetails(){
        return details;
    }

    @Override
    public String toString(){
        return name + ", " + dose;
    }

    //-------METODOS QUE DEBE IMPLEMENTAR PARA QUE SEA PARCELIZABLE---------------------------------
    public Medicine(Parcel in){
        String [] data = new String[4];
        in.readStringArray(data);

        this.name = data[0];
        this.id = Integer.parseInt(data[1]);
        this.dose = data[2];
        this.details = data[3];

    }

    @Override
    public int describeContents(){
        //stub!
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeStringArray(new String[]{this.name, Integer.toString(this.id), this.dose, this.details});
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Medicine createFromParcel(Parcel source) {
            return new Medicine(source);
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine[size];
        }
    };

}
