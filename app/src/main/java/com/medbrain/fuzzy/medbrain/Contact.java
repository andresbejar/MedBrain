package com.medbrain.fuzzy.medbrain;

/**
 * Created by Yonan on 03/05/2015.
 */
public class Contact {
    private int ID; //se asigna al crear tablas
    private String Name;
    private String Phone;
    private String Email;
    private String Especialidad;
    private String Reputacion;
    private String Precio;

    //este sera el create contact
    public Contact(String name, String phone, String email, String espec, String rep, String precio){
        Name = name;
        Phone = phone;
        Email = email;
        Especialidad = espec;
        Reputacion = rep;
        Precio = precio;
    }


    public Contact(String name){
        Name = name;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getName(){
        return Name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getPhone(){
        return Phone;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getEmail(){
        return Email;
    }

    public void setEspecialidad(String esp) {
        Especialidad = esp;
    }
    public String getEspecialidad(){
        return Especialidad;
    }

    public void setReputacion(String rep) {
        Reputacion = rep;
    }
    public String getReputacion(){
        return Reputacion;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }
    public String getPrecio(){
        return Precio;
    }
}
