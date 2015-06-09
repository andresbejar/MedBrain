package com.medbrain.fuzzy.medbrain;

/**
 * Created by Ulises on 5/1/2015.
 */
public class Users {
    private String FirstName;
    private String SecondName;
    private String ThirdName;
    private int ID;
    private String BirthDay;
    private String BirthMonth;
    private String BirthYear;
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
     * @param birthDay fecha de nac
     */
    public void setBirthDay(String birthDay){
        BirthDay = birthDay;
    }

    public void setBirthMonth(String birthMonth){BirthMonth = birthMonth;}

    public void setBirthYear(String birthYear){BirthYear = birthYear;}

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
    public String getBirthDay(){
        return BirthDay;
    }

    public String getBirthMonth(){
        return BirthMonth;
    }

    public String getBirthYear(){
        return BirthYear;
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
