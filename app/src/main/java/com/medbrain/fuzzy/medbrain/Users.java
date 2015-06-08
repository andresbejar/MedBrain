package com.medbrain.fuzzy.medbrain;

/**
 * Created by Ulises on 5/1/2015.
 */
public class Users {
    private String FirstName;
    private String SecondName;
    private String ThirdName;
    private int ID;
    private int BirthDate;
    /*private int height;
    private int weight;*/

    /**
     * constructor
     * @param firstName inicializa el nombre del usuario
     */
    public Users (String firstName){
        FirstName = firstName;
    }

    //setters

    /**
     * inicializa el id del User
     * @param id identificacion del user
     */
    public void setID(int id){
        ID = id;
    }

    /**
     * fecha de naciemiento del user
     * @param birthDate fecha de nac
     */
    public void setBirthDate(int birthDate){
        BirthDate = birthDate;
    }

    /**
     * primer apellido del user
     * @param secondName primer aopellido
     */
    public void setSecondName(String secondName){
        SecondName = secondName;
    }

    /**
     * segundo apellido del user
     * @param thirdName segundo apellido
     */
    public void setThirdName(String thirdName){
        ThirdName = thirdName;
    }

    //getters: todos los metodos siguiente devuelven los valores de la instancia
    public int getBirthDate(){
        return BirthDate;
    }

    public String getFirstName(){
        return FirstName;
    }

    public String getSecondName(){
        return SecondName;
    }

    public String getThirdName(){
        return ThirdName;
    }

    public int getID(){
        return ID;
    }

}
