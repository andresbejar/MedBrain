package com.medbrain.fuzzy.medbrain;


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase Medicina, almacena y maneja toda la informacion de una medicina en particular
 * @author Andres Bejarano
 */
public class Medicine {
    private String Name;
    private int ID;
    private String Dose;
    private String Details;

    /**
     * Constructor
     * @param name nombre de la medicina
     */
    public Medicine(String name){
        Name = name;
    }

    /**
     * Retorna el nombre de la medicina
     * @return String nombre de la medicina
     */
    public String getName(){
        return Name;
    }

    /**
     * Retorna el ID de la medicina
     * @return int ID de la medicina
     */
    public int getID(){
        return ID;
    }

    /**
     * Retorna la dosis de la medicina
     * @return String dosis de la medicina
     */
    public String getDose(){
        return Dose;
    }

    /**
     * Especifica la dosis de la medicina
     * @param dose String dosis especificada
     */
    public void setDose(String dose) {
        Dose = dose;
    }

    /**
     * Especifica la ID de la medicina
     * @param ID especificada
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Inicializa una nueva medicina, cuya informacion aun no existe en la BD
     * Crea un nuevo ID de la medicina basado en la hora de creacion del objeto
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
        Details = _details;
    }

    /**
     * Retorna los detalles de la medicina
     * @return String detalles de la medicina
     */
    public String getDetails(){
        return Details;
    }


}
