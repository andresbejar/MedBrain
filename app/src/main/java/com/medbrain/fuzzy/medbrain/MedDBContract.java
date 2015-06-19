package com.medbrain.fuzzy.medbrain;

import android.provider.BaseColumns;

/**
 * Clase que sirve para crear el esquema de la base de datos
 * @author Andres Bejarano
 * @version 0.1.3
 * @see com.medbrain.fuzzy.medbrain.DatabaseHandler
 */
public final class MedDBContract {

    /**
     * Constructor vacio para evitar instanciaciones accidentales
     */
    public MedDBContract(){}
    public static final String TEXT_TYPE = " TEXT";

    /**
     * Esquema para tabla Medicinas
     * @author Andres Bejarano
     */
    public static abstract class MedicineContract implements BaseColumns{

        public static final String TABLE_NAME = "Medicines";
        public static final String COLUMN_NAME_TITLE = "Name";
        public static final String COLUMN_NAME_DOSE = "Dose";
        public static final String COLUMN_NAME_DETAILS = "Details";
    }

    /**
     * Esquema para tabla Recetas
     * @author Andres Bejarano
     */
    public static abstract class PrescriptionContract implements BaseColumns{
        public static final String TABLE_NAME = "Prescriptions";
        public static final String COLUMN_NAME_DOCTOR ="Doctor";
        public static final String COLUMN_NAME_DATE = "Date";
        public static final String COLUMN_NAME_MOTIVE = "Motive";

    }

    /**
     * Esquema para tabla MedPresc
     * @author Andres Bejarano
     */
    public static abstract class MedPrescContract implements BaseColumns{
        public static final String TABLE_NAME = "MedPresc";
        public static final String COLUMN_NAME_PRESC_ID = "PrescID";
        public static final String COLUMN_NAME_MED_ID = "MedID";
    }

    /**
     * Esquema para tabla de Users
     * @author Ulises Gonzalez
     */
    public static abstract class UsersContract implements BaseColumns{
        public static final String TABLE_NAME = "Users";
       // public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_FIRST_NAME = "FirstName";
        public static final String COLUMN_NAME_SECOND_NAME = "SecondName";
        public static final String COLUMN_NAME_THIRD_NAME = "ThirdName";
        public static final String COLUMN_NAME_BIRTH_DAY = "Day";
        public static final String COLUMN_NAME_BIRTH_MONTH = "Month";
        public static final String COLUMN_NAME_BIRTH_YEAR = "Year";
        //public static final String COLUMN_NAME_PRESC_ID = "PrescID"; //una persona puede tener muchas presc
    }

    public static abstract class ContactContract implements BaseColumns{
        public static final String TABLE_NAME = "Contact";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PHONE = "Phone";
        public static final String COLUMN_NAME_EMAIL = "Email";
        public static final String COLUMN_NAME_ESPECIALIDAD = "Especialidad";
        public static final String COLUMN_NAME_REPUTACION = "Reputacion";
    }

    public static abstract class AppointmentContract implements BaseColumns{
        public static final String TABLE_NAME = "Appointments";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PLACE = "Place";
        public static final String COLUMN_NAME_DOCTOR = "Doctor";
        public static final String COLUMN_NAME_DATE = "Date";
    }

    public static abstract class ReminderContract implements BaseColumns{
        public static final String TABLE_NAME = "Reminders";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_DATE = "InnerDate";
    }
}
