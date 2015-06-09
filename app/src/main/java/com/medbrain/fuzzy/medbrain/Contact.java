package com.medbrain.fuzzy.medbrain;

/**
 * Clase que guarda toda la informacion relevante a un contacto
 * @author Yonan Cano
 */
public class Contact {
    private int ID;
    private String Name;
    private String Phone;
    private String Email;
    private String Especialidad;
    private String Reputacion;

    /**
     * Constructor
     * @param name nombre del contacto
     * @param phone numero de telefono del contacto
     * @param email email del contacto
     * @param espec especialidad del contacto
     * @param rep reputacion del contacto
     */
    public Contact(String name, String phone, String email, String espec, String rep){
        Name = name;
        Phone = phone;
        Email = email;
        Especialidad = espec;
        Reputacion = rep;
    }


    /**
     * Constructor alternativo
     * @param name nombre del contacto
     */
    public Contact(String name){
        Name = name;
    }

    /**
     * Especifica el ID del contacto
     * @param id por especificar
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Especifica el nombre del contacto
     * @param name nombre por especificar
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Obtiene el nombre del contacto
     * @return String nombre del contacto
     */
    public String getName(){
        return Name;
    }

    /**
     * Especifica el telefono del contacto
     * @param phone numero de telefono del contacto
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     * Obtiene el numero de telefono del contacto
     * @return numero de telefono del contacto
     */
    public String getPhone(){
        return Phone;
    }

    /**
     * Especifica el email del contacto
     * @param email email del contacto
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Obtiene el email del contacto
     * @return el email del contacto
     */
    public String getEmail(){
        return Email;
    }

    /**
     * Especifica la especialidad del contacto
     * @param esp String especialidad del contacto
     */
    public void setEspecialidad(String esp) {
        Especialidad = esp;
    }

    /**
     * Obtiene la especialidad del contacto
     * @return la especialidad del contacto
     */
    public String getEspecialidad(){
        return Especialidad;
    }

    /**
     * Especifica la reputacion del contacto
     * @param rep reputacion del contacto
     */
    public void setReputacion(String rep) {
        Reputacion = rep;
    }

    /**
     * Obtiene la reputacion del contacto
     * @return String reputacion del contacto
     */
    public String getReputacion(){
        return Reputacion;
    }

}
