package com.medbrain.fuzzy.medbrain;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public static final int DATABASE_VERSION = 4;
    private static final String TAG = "MedBrain-App";


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
                ", " + MedDBContract.PrescriptionContract.COLUMN_NAME_MOTIVE + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.PrescriptionContract.COLUMN_NAME_USER + " INTEGER" +
                ")";

        final String CREATE_MEDPRESC_TABLE = "CREATE TABLE " + MedDBContract.MedPrescContract.TABLE_NAME +
                "(" + MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID + " INTEGER, " +
                MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID + " INTEGER, " +
                "PRIMARY KEY(" + MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID +
                ", " + MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID + "), " +
                "FOREIGN KEY(" + MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID + ") " +
                "REFERENCES " + MedDBContract.PrescriptionContract.TABLE_NAME + "(" +
                MedDBContract.PrescriptionContract._ID + ") ON DELETE CASCADE, " +        //TODO: agregar ON DELETE CASCADE
                "FOREIGN KEY(" + MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID + ") " +
                "REFERENCES " + MedDBContract.MedicineContract.TABLE_NAME + "(" +
                MedDBContract.MedicineContract._ID + ") ON DELETE CASCADE)";              //TODO: agregar ON DELETE CASCADE


        final String CREATE_USERS_TABLE = "CREATE TABLE " + MedDBContract.UsersContract.TABLE_NAME +
                "(" + MedDBContract.UsersContract._ID + " INTEGER PRIMARY KEY," +
                MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR + MedDBContract.TEXT_TYPE + "," +
                MedDBContract.UsersContract.COLUMN_NAME_LOG + MedDBContract.TEXT_TYPE + ")";


        final String CREATE_CONTACT_TABLE = "CREATE TABLE " + MedDBContract.ContactContract.TABLE_NAME +
                " (" + MedDBContract.ContactContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.ContactContract.COLUMN_NAME_NAME + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_PHONE1 + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_PHONE2 + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_EMAIL + MedDBContract.TEXT_TYPE +
                ", " + MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD + MedDBContract.TEXT_TYPE +
                ")";

        final String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + MedDBContract.AppointmentContract.TABLE_NAME +
                "(" + MedDBContract.AppointmentContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.AppointmentContract.COLUMN_NAME_NAME + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_PLACE + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_DATE + " INTEGER" + ", " +
                MedDBContract.AppointmentContract.COLUMN_NAME_USER + " INTEGER" + ")";

        final String CREATE_REMINDERS_TABLE = "CREATE TABLE " + MedDBContract.ReminderContract.TABLE_NAME +
                "(" + MedDBContract.ReminderContract._ID + " INTEGER PRIMARY KEY, " +
                MedDBContract.ReminderContract.COLUMN_NAME_NAME + MedDBContract.TEXT_TYPE + ", " +
                MedDBContract.ReminderContract.COLUMN_NAME_DATE + " INTEGER" + ", " +
                MedDBContract.ReminderContract.COLUMN_NAME_USER + " INTEGER" + ")";


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
        values.put(MedDBContract.ContactContract.COLUMN_NAME_PHONE1, _cont.getPhone1());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_PHONE2, _cont.getPhone2());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_EMAIL, _cont.getEmail());
        values.put(MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD, _cont.getEspecialidad());

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
                MedDBContract.ContactContract.COLUMN_NAME_PHONE1,
                MedDBContract.ContactContract.COLUMN_NAME_PHONE2,
                MedDBContract.ContactContract.COLUMN_NAME_EMAIL,
                MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD
                };

        Cursor cursor = db.query(MedDBContract.MedicineContract.TABLE_NAME, projection,
                MedDBContract.ContactContract.COLUMN_NAME_NAME + " = ?", new String[]{contName}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact returnCont = new Contact(contName);

        returnCont.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract._ID)));
        returnCont.setPhone1(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_PHONE1)));
        returnCont.setPhone2(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_PHONE2)));
        returnCont.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_EMAIL)));
        returnCont.setEspecialidad(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD)));
        cursor.close();

        return returnCont;
    }

    /**
     * Obtiene un contacto de la BD segun su ID
     * @param contId ID del contacto a obtener
     * @return contacto obtenido
     */
    public Contact getContactById(int contId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {MedDBContract.ContactContract._ID,
                MedDBContract.ContactContract.COLUMN_NAME_NAME,
                MedDBContract.ContactContract.COLUMN_NAME_PHONE1,
                MedDBContract.ContactContract.COLUMN_NAME_PHONE2,
                MedDBContract.ContactContract.COLUMN_NAME_EMAIL,
                MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD
        };

        Cursor cursor = db.query(MedDBContract.ContactContract.TABLE_NAME, projection, MedDBContract.ContactContract._ID + "=?",
                new String[]{Integer.toString(contId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact returnCont = new Contact();

        returnCont.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract._ID)));
        returnCont.setName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_NAME)));
        returnCont.setPhone1(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_PHONE1)));
        returnCont.setPhone2(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_PHONE2)));
        returnCont.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_EMAIL)));
        returnCont.setEspecialidad(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD)));
        cursor.close();

        return returnCont;
    }


    public void addAppointment(Appointment _app){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        CurrentUser user = CurrentUser.getInstance();

        values.put(MedDBContract.AppointmentContract._ID, _app.getID());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_NAME, _app.getName());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_PLACE, _app.getPlace());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR, _app.getDoctorID());
        values.put(MedDBContract.AppointmentContract.COLUMN_NAME_DATE, _app.getDate().getTimeInMillis());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_USER, user.getID());

        db.insert(MedDBContract.AppointmentContract.TABLE_NAME, null, values);
        db.close();
    }

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

    public void addReminder(Reminder _rmdr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        CurrentUser user = CurrentUser.getInstance();

        values.put(MedDBContract.ReminderContract._ID, _rmdr.getID());
        values.put(MedDBContract.ReminderContract.COLUMN_NAME_NAME, _rmdr.getName());
        values.put(MedDBContract.ReminderContract.COLUMN_NAME_DATE, _rmdr.getInnerDate());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_USER, user.getID());

        db.insert(MedDBContract.ReminderContract.TABLE_NAME, null, values);
        db.close();
    }

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
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY, _user.getBirthDay());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH, _user.getBirthMonth());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR, _user.getBirthYear());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_LOG, "F");

        db.insert(MedDBContract.UsersContract.TABLE_NAME, null, values);
        db.close();
    }

    public Users getUserByID(int userID){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = { MedDBContract.UsersContract._ID,
                MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR};

        Cursor cursor = db.query(MedDBContract.UsersContract.TABLE_NAME, projection, MedDBContract.UsersContract._ID + "=?",
                new String[]{Integer.toString(userID)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Users user = new Users(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME)));
        //user.setFirstName();
        user.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract._ID)));
        user.setSecondName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME)));
        user.setThirdName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME)));
        user.setBirthDay(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY)));
        user.setBirthMonth(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH)));
        user.setBirthYear(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR)));
        cursor.close();
        return user;
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
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR};

        Cursor cursor = db.query(MedDBContract.UsersContract.TABLE_NAME, projection, MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                new String[]{userName}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Users user = new Users(userName);
        user.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract._ID)));
        user.setSecondName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME)));
        user.setThirdName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME)));
        user.setBirthDay(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY)));
        user.setBirthMonth(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH)));
        user.setBirthYear(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR)));

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
        CurrentUser user = CurrentUser.getInstance();


        values.put(MedDBContract.PrescriptionContract._ID, _presc.getID());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR, _presc.getDoctor());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_DATE, _presc.getExpiration().getTimeInMillis());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_MOTIVE, _presc.getMotivo());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_USER, user.getID());

        db.insert(MedDBContract.PrescriptionContract.TABLE_NAME, null, values);
        db.close();

        //ahora se agregan todas las medicinas de la receta a la tabla MedPresc
        for(int med : _presc.Medicines){
            addMedToPresc(_presc, med);
        }
    }

    /**
     * Agrega una medicina a una receta en la tabla MedPresc
     * @param _presc Prescription a agregar la medicina
     * @param _med Medicine a agregar a _presc
     */
    public void addMedToPresc(Prescription _presc, int _med) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID, _presc.getID());
        values.put(MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID, _med);

        db.insert(MedDBContract.MedPrescContract.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Retorna un objeto medicina
     * @param medId ID de la medicina por obtener
     * @return Medicine obtenida
     */
    public Medicine getMedicine(int medId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {MedDBContract.MedicineContract._ID,
                MedDBContract.MedicineContract.COLUMN_NAME_TITLE,
                MedDBContract.MedicineContract.COLUMN_NAME_DOSE,
                MedDBContract.MedicineContract.COLUMN_NAME_DETAILS};

        Cursor cursor = db.query(MedDBContract.MedicineContract.TABLE_NAME, projection,
                MedDBContract.MedicineContract._ID + " = ?", new String[]{Integer.toString(medId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Medicine returnMed = new Medicine(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.MedicineContract.COLUMN_NAME_TITLE)));
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
        String [] projection = {MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID};
        Cursor cursor = db.query(MedDBContract.MedPrescContract.TABLE_NAME, projection,
                MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID + " = ?", new String[]{Integer.toString(id)}, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        Log.i(TAG, "DbHandler: loadMedicines cursor count is: " + cursor.getCount());


        while (!cursor.isAfterLast()) {
            Log.i(TAG, "DbHandler: loading medicines...");
            p.addMedicine(cursor.getInt(0));
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
                MedDBContract.PrescriptionContract.COLUMN_NAME_DATE,
                MedDBContract.PrescriptionContract.COLUMN_NAME_MOTIVE};

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
        returnPresc.setMotivo(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.PrescriptionContract.COLUMN_NAME_MOTIVE)));

        cursor.close();
        returnPresc = loadMedicines(returnPresc);

        return returnPresc;
    }

    /**
     * Obtiene todas las recetas activas en la BD
     * @return Cursor con informacion de todas las recetas
     */
    public Cursor getAllPrescriptions() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.PrescriptionContract._ID,
                                MedDBContract.PrescriptionContract.COLUMN_NAME_MOTIVE};
        Cursor cursor = db.query(MedDBContract.PrescriptionContract.TABLE_NAME, projection,
                null, null, null, null, null);
        return cursor;

    }

    public Cursor getUserPrescriptions(int userID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                MedDBContract.PrescriptionContract._ID,
                MedDBContract.PrescriptionContract.COLUMN_NAME_MOTIVE,
        };
        Cursor cursor = db.query(MedDBContract.PrescriptionContract.TABLE_NAME, projection,
                                MedDBContract.PrescriptionContract.COLUMN_NAME_USER + "=?",
                                new String[]{Integer.toString(userID)}, null, null, null);
        return cursor;
    }

    public boolean deletePrescription(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = MedDBContract.PrescriptionContract._ID + "=?";
        String [] selectionArgs = {Integer.toString(id)};
        db.delete(MedDBContract.PrescriptionContract.TABLE_NAME, selection, selectionArgs);
        return true;
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

    public Cursor getAllAppointments(int userID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.AppointmentContract._ID,
                MedDBContract.AppointmentContract.COLUMN_NAME_NAME,
                MedDBContract.AppointmentContract.COLUMN_NAME_PLACE,
                MedDBContract.AppointmentContract.COLUMN_NAME_DOCTOR,
                MedDBContract.AppointmentContract.COLUMN_NAME_DATE};
        Cursor cursor = db.query(MedDBContract.AppointmentContract.TABLE_NAME, projection,
                MedDBContract.AppointmentContract.COLUMN_NAME_USER + "=?",
                new String[]{Integer.toString(userID)}, null, null, null);
        return cursor;
    }

    public Cursor getAllMeds(){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {MedDBContract.MedicineContract._ID,
                                MedDBContract.MedicineContract.COLUMN_NAME_TITLE,
                                MedDBContract.MedicineContract.COLUMN_NAME_DETAILS,
                                MedDBContract.MedicineContract.COLUMN_NAME_DOSE};
        Cursor cursor = db.query(MedDBContract.MedicineContract.TABLE_NAME, projection,
                                null, null, null, null, null);
        return cursor;

    }

    public void updateUser(int id, String name, String lastName, String lastName2, String day, String month, String year){
        SQLiteDatabase db = this.getReadableDatabase();

        //valores a modificar
        ContentValues values = new ContentValues();
        values.put(MedDBContract.UsersContract._ID, id);
        values.put(MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME, name);
        values.put(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME, lastName);
        values.put(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME, lastName2);
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DAY, day);
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_MONTH, month);
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_YEAR, year);

        //fila a modificar
        String selection = MedDBContract.UsersContract._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        db.update(
                MedDBContract.UsersContract.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void updateLoggedUser(int oldID, int newID){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues oldValues = new ContentValues();
        ContentValues newValues = new ContentValues();
        oldValues.put(MedDBContract.UsersContract.COLUMN_NAME_LOG, "F");
        newValues.put(MedDBContract.UsersContract.COLUMN_NAME_LOG, "T");
        db.update(MedDBContract.UsersContract.TABLE_NAME, oldValues,
                MedDBContract.UsersContract._ID + "=?", new String[]{Integer.toString(oldID)});
        db.update(MedDBContract.UsersContract.TABLE_NAME, newValues,
                MedDBContract.UsersContract._ID + "=?", new String[]{Integer.toString(newID)});
    }

    public void updateNewLog(int newID){
        SQLiteDatabase db = this.getReadableDatabase();
        //valor a modificar del nuevo usuario
        ContentValues values = new ContentValues();
        values.put(MedDBContract.UsersContract.COLUMN_NAME_LOG, "T");
        //fila a modificar del nuevo usuario
        String selection = MedDBContract.UsersContract._ID + "=?";
        String [] selectionArgs = {String.valueOf(newID)};

        db.update(
                MedDBContract.UsersContract.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void updateOldLog(int oldID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String f = "F";
        //valor a modificar del nuevo usuario
        ContentValues values = new ContentValues();
        values.put(MedDBContract.UsersContract.COLUMN_NAME_LOG, f);
        values.put(MedDBContract.UsersContract._ID, oldID);
        //fila a modificar del nuevo usuario
        String selection = MedDBContract.UsersContract._ID + "=?";
        String[] selectionArgs = {String.valueOf(oldID)};

        db.update(
                MedDBContract.UsersContract.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public Cursor getLoggedUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {MedDBContract.UsersContract._ID};
        Cursor cursor = db.query(MedDBContract.UsersContract.TABLE_NAME, projection, MedDBContract.UsersContract.COLUMN_NAME_LOG +
                                "=?", new String[]{"T"}, null, null, null);
        return cursor;
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = MedDBContract.UsersContract._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        db.delete(MedDBContract.UsersContract.TABLE_NAME, selection, selectionArgs);
    }

    /**
     * Elimina un contacto de la BD
     * @param _id ID del contacto
     */
    public void deleteContact(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MedDBContract.ContactContract.TABLE_NAME, MedDBContract.ContactContract._ID + "= "+_id, null);
    }

    /**
     * Edita un contacto de la BD
     * @param _id ID del contacto
     */
    public void updateContact(int _id, String name, String phone1, String phone2, String email, String espec) {
        SQLiteDatabase db = this.getReadableDatabase();

        //valores a modificar
        ContentValues values = new ContentValues();
        values.put(MedDBContract.ContactContract.COLUMN_NAME_NAME, name);
        values.put(MedDBContract.ContactContract.COLUMN_NAME_PHONE1, phone1);
        values.put(MedDBContract.ContactContract.COLUMN_NAME_PHONE2, phone2);
        values.put(MedDBContract.ContactContract.COLUMN_NAME_EMAIL, email);
        values.put(MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD, espec);

        //fila a modificar
        String selection = MedDBContract.ContactContract._ID + "=?";
        String[] selectionArgs = {String.valueOf(_id)};

        db.update(
                MedDBContract.ContactContract.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    /**
     * Obitene todos los contactos que se encuentran registrados en la Base de Datos
     * @return Cursor con informacion de todos los contatos
     */
    public Cursor getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {MedDBContract.ContactContract._ID,
                MedDBContract.ContactContract.COLUMN_NAME_NAME,
                MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD};
        Cursor cursor = db.query(MedDBContract.ContactContract.TABLE_NAME, projection,
                null, null, null, null, null);
        return cursor;

    }
}

