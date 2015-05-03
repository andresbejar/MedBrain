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

    public Users (String firstName){
        FirstName = firstName;
    }

    //setters
    public void setID(int id){
        ID = id;
    }

    public void setBirthDate(int birthDate){
        BirthDate = birthDate;
    }

    public void setSecondName(String secondName){
        SecondName = secondName;
    }

    public void setThirdName(String thirdName){
        ThirdName = thirdName;
    }

    //getters
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
