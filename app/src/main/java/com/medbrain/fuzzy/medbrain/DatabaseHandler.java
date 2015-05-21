package com.medbrain.fuzzy.medbrain;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que crea y maneja todas las operaciones de la BD
 * @author Andres Bejarano
 * @version 0.1.3
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MedApp.db";
    public static final int DATABASE_VERSION = 3;

    /**
     * Constructor SQLiteOpenHelper
     * @param context Contexto en que es creada la base de datos
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crea la base de datos, crea las tablas basadas en el esquema
     * @see com.medbrain.fuzzy.medbrain.MedDBContract
     * @param db SQLiteDatabase a crear
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MEDICINE_TABLE = "CREATE TABLE " + MedDBContract.MedicineContract.TABLE_NAME +
                " (" + MedDBContract.MedicineContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.MedicineContract.COLUMN_NAME_TITLE + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.MedicineContract.COLUMN_NAME_DOSE + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.MedicineContract.COLUMN_NAME_DETAILS + MedDBContract.TEXT_TYPE +
                ")";

        final String CREATE_PRESCRIPTION_TABLE = "CREATE TABLE " + MedDBContract.PrescriptionContract.TABLE_NAME +
                "( " + MedDBContract.PrescriptionContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.PrescriptionContract.COLUMN_NAME_DATE + " INTEGER" +
                ")";

        final String CREATE_MEDPRESC_TABLE = "CREATE TABLE " + MedDBContract.MedPrescContract.TABLE_NAME +
                "(" + MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID + " INTEGER, " +
                MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID + " INTEGER, " +
                "PRIMARY KEY(" + MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID +
                ", " + MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID + "), " +
                "FOREIGN KEY(" + MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID + ") " +
                "REFERENCES " + MedDBContract.PrescriptionContract.TABLE_NAME + "(" +
                MedDBContract.PrescriptionContract._ID + "), " +        //TODO: agregar ON DELETE CASCADE
                "FOREIGN KEY(" + MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID + ") " +
                "REFERENCES " + MedDBContract.MedicineContract.TABLE_NAME + "(" +
                MedDBContract.MedicineContract._ID + "))";              //TODO: agregar ON DELETE CASCADE


        final String CREATE_USERS_TABLE = "CREATE TABLE " + MedDBContract.UsersContract.TABLE_NAME +
                "(" + MedDBContract.UsersContract._ID + " INTEGER PRIMARY KEY," +
                MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE + " INTEGER" + ")";


        final String CREATE_CONTACT_TABLE = "CREATE TABLE " + MedDBContract.ContactContract.TABLE_NAME +
                " (" + MedDBContract.ContactContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.ContactContract.COLUMN_NAME_NAME + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_PHONE + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_EMAIL + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_REPUTACION + MedDBContract.TEXT_TYPE +
                ")";

        final String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + MedDBContract.AppointmentContract.TABLE_NAME +
                "(" + MedDBContract.AppointmentContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.AppointmentContract.COLUMN_NAME_NAME + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_PLACE + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_DATE + " INTEGER" + ")";

        final String CREATE_REMINDERS_TABLE = "CREATE TABLE " + MedDBContract.ReminderContract.TABLE_NAME +
                "(" + MedDBContract.ReminderContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.ReminderContract.COLUMN_NAME_NAME + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.ReminderContract.COLUMN_NAME_DATE + "INTEGER" + ")";


        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_PRESCRIPTION_TABLE);
        db.execSQL(CREATE_MEDPRESC_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_CONTACT_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
        db.execSQL(CREATE_REMINDERS_TABLE);
    }

    /**
     * Metodo Actualiza la version de la base de datos
     * @param db SQLiteDatabase por actualizar
     * @param oldVersion version antigua
     * @param newVersion version nueva
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.MedicineContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.PrescriptionContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.MedPrescContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.UsersContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.ContactContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.AppointmentContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.ReminderContract.TABLE_NAME);
        onCreate(db);
    }

    /**
     * Configura la base de datos para utilizar restricciones de llave foranea
     * @param db SQLiteDatabase por configurar
     */
    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * Agrega un nuevo contacto a la BD
     * @param _cont contacto por agregar
     */
    public void addContact(Contact _cont) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.ContactContract.COLUMN_NAME_NAME, _cont.getName());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_PHONE, _cont.getPhone());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_EMAIL, _cont.getEmail());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD, _cont.getEspecialidad());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_REPUTACION, _cont.getReputacion());

        db.insert(MedDBContract.ContactContract.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Obtiene un contacto de la BD
     * @param contName nombre del contacto a obtener
     * @return contacto obtenido
     */
    public Contact getContact(String contName) { //ojo que mi nombre es full
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {MedDBContract.ContactContract._ID,
                MedDBContract.ContactContract.COLUMN_NAME_NAME,
                MedDBContract.ContactContract.COLUMN_NAME_PHONE,
                MedDBContract.ContactContract.COLUMN_NAME_EMAIL,
                MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD,
                MedDBContract.ContactContract.COLUMN_NAME_REPUTACION
                };

        Cursor cursor = db.query(MedDBContract.MedicineContract.TABLE_NAME, projection,
                MedDBContract.ContactContract.COLUMN_NAME_NAME + " = ?", new String[]{contName}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact returnCont = new Contact(contName);

        returnCont.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract._ID)));
        returnCont.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_PHONE)));
        returnCont.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_EMAIL)));
        returnCont.setEspecialidad(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD)));
        returnCont.setReputacion(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_REPUTACION)));
        cursor.close();

        return returnCont;
    }
    /**
     * Metodo para agregar una cita nueva a la base de datos SQLite
     * @param un objeto de tipo Appointment que tiene sus datos inicializados
     * @return void
     */
    public void addAppointment(Appointment _app){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.AppointmentContract._ID, _app.getID());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_NAME, _app.getName());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_PLACE, _app.getPlace());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR, _app.getDoctorID());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_DATE, _app.getDate().getTimeInMillis());

        db.insert(MedDBContract.AppointmentContract.TABLE_NAME, null, values);
        db.close();
    }
    /**
     * Metodo para jalar una cita nueva desde la base de datos SQLite
     * @param un objeto de tipo Integer que es el ID de la cita a buscar
     * @return un objeto de tipo Appointment
     */
    public Appointment getAppointment(Integer ID){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {MedDBContract.AppointmentContract._ID,
                MedDBContract.AppointmentContract.COLUMN_NAME_NAME,
                MedDBContract.AppointmentContract.COLUMN_NAME_PLACE,
                MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR,
                MedDBContract.AppointmentContract.COLUMN_NAME_DATE};

        Cursor c = db.query(MedDBContract.AppointmentContract.TABLE_NAME, projection, MedDBContract.AppointmentContract._ID,
                new String[]{Integer.toString(ID)}, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }

        Appointment a = new Appointment();

        a.setID(c.getInt(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract._ID)));
        a.setName(c.getString(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract.COLUMN_NAME_NAME)));
        a.setPlace(c.getString(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract.COLUMN_NAME_PLACE)));
        a.setDoctorID(c.getString(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR)));
        a.setInnerCalendar(c.getInt(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract.COLUMN_NAME_DATE)));

        c.close();
        return a;
    }
    /**
     * Igual que el getter de Appointment pero para Reminders
     * @param un objeto de tipo Reminder que tiene sus datos inicializados
     * @return void
     */
    public void addReminder(Reminder _rmdr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.ReminderContract._ID, _rmdr.getID());
        values.put(MedDBContract.ReminderContract.COLUMN_NAME_NAME, _rmdr.getName());
        values.put(MedDBContract.ReminderContract.COLUMN_NAME_DATE, _rmdr.getInnerDate());

        db.insert(MedDBContract.AppointmentContract.TABLE_NAME, null, values);
        db.close();
    }
    /**
     * Metodo para jalar un reminder nueva desde la base de datos SQLite
     * @param un objeto de tipo Integer que tiene sus datos inicializados
     * @return un objeto de tipo Reminder
     */
    public Reminder getReminder(Integer ID){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {MedDBContract.ReminderContract._ID,
                MedDBContract.ReminderContract.COLUMN_NAME_NAME,
                MedDBContract.ReminderContract.COLUMN_NAME_DATE};

        Cursor c = db.query(MedDBContract.AppointmentContract.TABLE_NAME, projection, MedDBContract.ReminderContract._ID,
                new String[]{Integer.toString(ID)}, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }

        Reminder r = new Reminder();

        r.setID(c.getInt(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract._ID)));
        r.setName(c.getString(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract.COLUMN_NAME_NAME)));
        r.setInnerCalendar(c.getInt(c.getColumnIndexOrThrow(MedDBContract.AppointmentContract.COLUMN_NAME_DATE)));

        c.close();
        return r;
    }

    /**
     * agrega usuarios a la base
     * <p>
     *     Toma los valores de la instancia Users y guarda sus atributos en la tabla Users
     * @param _user instancia de Users que contiene todos los atributos de un usuario
     */
    public void addUser(Users _user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.UsersContract._ID, _user.getID());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME, _user.getFirstName());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME, _user.getSecondName());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME, _user.getThirdName());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE, _user.getBirthDate());

        db.insert(MedDBContract.UsersContract.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Permite buscar los datos de un usuario dentro de la tabla
     * @param userName nombre del usuarioa a buscar
     * @return User instancia del usuario cuyos atributos coinciden con los del usuario buscado dentro de la tabla
     */
    public Users getUser(String userName){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = { MedDBContract.UsersContract._ID,
                MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE};

        Cursor cursor = db.query(MedDBContract.UsersContract.TABLE_NAME, projection, MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                new String[]{userName}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Users user = new Users(userName);
        user.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract._ID)));
        user.setSecondName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME)));
        user.setThirdName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME)));
        user.setBirthDate(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE)));

        cursor.close();
        return user;
    }

    /**
     * Agrega una medicina a la base de datos
     * @param _med Medicine a agregar a la BD
     *
     */
    public void addMedicine(Medicine _med) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.MedicineContract.COLUMN_NAME_TITLE, _med.getName());
        values.put(MedDBContract.MedicineContract.COLUMN_NAME_DOSE, _med.getDose());
        values.put(MedDBContract.MedicineContract._ID, _med.getID());
        values.put(MedDBContract.MedicineContract.COLUMN_NAME_DETAILS, _med.getDetails());

        db.insert(MedDBContract.MedicineContract.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Agrega una receta a la base de datos
     * @param _presc Prescription a agregar a la BD
     */
    public void addPrescription(Prescription _presc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(MedDBContract.PrescriptionContract._ID, _presc.getID());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR, _presc.getDoctor());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_DATE, _presc.getExpiration().getTimeInMillis());

        db.insert(MedDBContract.PrescriptionContract.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Agrega una medicina a una receta en la tabla MedPresc
     * @param _presc Prescription a agregar la medicina
     * @param _med Medicine a agregar a _presc
     */
    public void addMedToPresc(Prescription _presc, Medicine _med) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID, _presc.getID());
        values.put(MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID, _med.getID());

        db.insert(MedDBContract.MedPrescContract.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Retorna un objeto medicina
     * @param medName String nombre de la medicina por obtener
     * @return Medicine obtenida
     */
    public Medicine getMedicine(String medName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {MedDBContract.MedicineContract._ID,
                MedDBContract.MedicineContract.COLUMN_NAME_TITLE,
                MedDBContract.MedicineContract.COLUMN_NAME_DOSE,
                MedDBContract.MedicineContract.COLUMN_NAME_DETAILS};

        Cursor cursor = db.query(MedDBContract.MedicineContract.TABLE_NAME, projection,
                MedDBContract.MedicineContract.COLUMN_NAME_TITLE + " = ?", new String[]{medName}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Medicine returnMed = new Medicine(medName);
        returnMed.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.MedicineContract._ID)));
        returnMed.setDose(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.MedicineContract.COLUMN_NAME_DOSE)));
        returnMed.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.MedicineContract.COLUMN_NAME_DETAILS)));

        cursor.close();
        return returnMed;
    }

    /**
     * Crea un objeto Date a partir del campo Date de un cursor de la tabla Medicines
     * @param cursor con informacion de la tabla Medicines
     * @param index indice del campo Date en el cursor
     * @return objeto Date
     */
    public Date loadDate(Cursor cursor, int index) {
        if (cursor != null) {
            return new Date(cursor.getLong(index));
        }
        return null;
    }

    /**
     * Carga todas las medicinas de una receta
     * @param p Prescription de la que se obtienen las medicinas
     * @return Prescription con todas sus medicinas
     */
    public Prescription loadMedicines(Prescription p) {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = p.getID();
        String query = "SELECT m.Name " +
                "FROM Medicines m JOIN MedPresc P on m.ID = P.MedID " +
                "WHERE P.PrescID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(id)});
        if (cursor != null)
            cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            p.addMedicine(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return p;

    }

    /**
     * Obtiene una receta a partir de su ID
     * @param ID de la receta a obtener
     * @return Prescription creada a partir de la informacion de la BD
     */
    public Prescription getPrescription(int ID) {
        String name = Integer.toString(ID);
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.PrescriptionContract._ID,
                MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR,
                MedDBContract.PrescriptionContract.COLUMN_NAME_DATE};

        Cursor cursor = db.query(MedDBContract.PrescriptionContract.TABLE_NAME, projection,
                MedDBContract.PrescriptionContract._ID + " = ?", new String[]{name}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Prescription returnPresc = new Prescription();
        returnPresc.setID(ID);
        returnPresc.setDoctor(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR)));

        Date date = loadDate(cursor, cursor.getColumnIndexOrThrow(MedDBContract.PrescriptionContract.COLUMN_NAME_DATE));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        returnPresc.setExpiration(calendar);

        cursor.close();
        returnPresc = loadMedicines(returnPresc);

        return returnPresc;
    }

    //TODO: Agregar parametro UserID para que nada mas jale las recetas del usuario actual

    /**
     * Obtiene todas las recetas activas en la BD
     * @return Cursor con informacion de todas las recetas
     */
    public Cursor getAllPrescriptions() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.PrescriptionContract._ID,
                                MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR };
        Cursor cursor = db.query(MedDBContract.PrescriptionContract.TABLE_NAME, projection,
                null, null, null, null, null);
        return cursor;

    }

    /**
     * obitene todos los usuarios que se encuentran registrados en la base de datos
     * @return Cursos con informacion de todos los usuarios
     */
    public Cursor getAllUsers() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.UsersContract._ID,
                MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME};
        Cursor cursor = db.query(MedDBContract.UsersContract.TABLE_NAME, projection,
                null, null, null, null, null);
        return cursor;
    }
    /**
     * Metodo para jalar un cursor que permita recorrer todas las citas creadas
     * @param null
     * @return un objeto de tipo Cursor
     */
    public Cursor getAllAppointments(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.AppointmentContract._ID,
                MedDBContract.AppointmentContract.COLUMN_NAME_NAME,
                MedDBContract.AppointmentContract.COLUMN_NAME_PLACE,
                MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR,
                MedDBContract.AppointmentContract.COLUMN_NAME_DATE};
        Cursor cursor = db.query(MedDBContract.AppointmentContract.TABLE_NAME, projection,
                null, null, null, null, null);
        return cursor;
    }
}

