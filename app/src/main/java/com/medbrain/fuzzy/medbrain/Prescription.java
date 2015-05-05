package com.medbrain.fuzzy.medbrain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que maneja toda la informacion de una Receta especifica
 * @author Andres Bejarano
 */
public class Prescription {
    private int ID;
    private String Doctor; //TODO: Cambiar el String por objeto Contacto

    /**
     * Vector que almacena las medicinas asociadas con esta receta
     */
    private ArrayList<String> Medicines;

    private Calendar Expiration;
    public int userID;

    /**
     * Constructor
     * Inicializa el vector de medicinas
     */
    public Prescription(){
        Medicines = new ArrayList<String>();
    }

    /**
     * Especifica el ID del usuario dueño de la receta
     * @param id del usuario
     */
    public void setUserID(int id){
        userID = id;
    }

    /**
     * Especifica el ID de la receta
     * @param _id de la receta
     */
    public void setID(int _id){
        ID = _id;
    }

    /**
     * Especifica el doctor que prescribio la receta
     * @param doctor nombre del doctor que prescribio la receta
     */
    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    /**
     * Retorna el ID de la receta
     * @return ID de la receta
     */
    public int getID() {
        return ID;
    }

    /**
     * Retorna el nombre del doctor que prescribio la receta
     * @return String doctor que prescribio la receta
     */
    public String getDoctor() {
        return Doctor;
    }


    /**
     * Especifica la fecha de expiracion de la receta
     * @param exp fecha de expiracion de la receta
     */
    public void setExpiration(Calendar exp){
        Expiration = exp;
    }


    /**
     * Inicializa una receta nueva cuya informacion aun no existe en la BD
     * Crea una fecha de expiracion default
     * Crea su ID a base de la fecha y hora en que se creo la receta
     */
    public void initializeNewPrescription(){
        Expiration = new GregorianCalendar();
        Expiration.setTime(new Date());
        int year = Expiration.get(Calendar.YEAR);
        int month = Expiration.get(Calendar.MONTH);
        int day = Expiration.get(Calendar.DAY_OF_YEAR);
        int hour = Expiration.get(Calendar.HOUR);
        int minute = Expiration.get(Calendar.MINUTE);
        int second = Expiration.get(Calendar.SECOND);
        int ms = Expiration.get(Calendar.MILLISECOND);

        int id = (year + month + day + hour + minute + second + ms);
        this.setID(id);
    }

    /**
     * Retorna la expiracion de la receta
     * @return Calendar fecha de expiracion de la receta
     */
    public Calendar getExpiration(){
        return Expiration;
    }

    /**
     * Retora verdadero si la receta no tiene medicinas asociadas
     * @return boolean
     */
    public boolean IsEmpty(){
        return Medicines.isEmpty();
    }

    /**
     * Agrega una medicina a la receta
     * @param _med Medicine a agregar a la receta
     */
    public void addMedicine(String _med){
        Medicines.add(_med);
    }

    /**
     * Retorna una medicina asociada a la receta
     * @param index indice de la medicina
     * @return String nombre de la medicina
     */
    public String getMedicine(int index){
        return Medicines.get(index);
    }

    /**
     * Borra una medicina de la lista de medicinas
     * @param _med medicina a borrar
     * @return verdadero si la medicina se borro, falso si no existe
     */
    public boolean deleteMedicine(String _med){
        int size = Medicines.size();
        for(int i = 0; i < size; i++){
            if(Medicines.get(i).compareTo(_med) == 0){
                Medicines.remove(i);
                return true;
            }
        }
        return false;
    }


}
