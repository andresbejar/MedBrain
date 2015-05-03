package com.medbrain.fuzzy.medbrain;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase handler para todas las operaciones de la BD
 * TODO: Agregar metodos para operaciones CRUD
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MedApp.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    * Metodo que crea las tablas de la base de datos.
    * TODO: Agregar strings para crear tablas restantes
    * */
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

        //NOTA: tal vez haya que agregarle el _ID a esta tabla tambien. REVISAR!!
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

        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_PRESCRIPTION_TABLE);
        db.execSQL(CREATE_MEDPRESC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.MedicineContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.PrescriptionContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedDBContract.MedPrescContract.TABLE_NAME);

        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void addUser(Users _user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.UsersContract.COLUMN_NAME_ID, _user.getID());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_First_NAME, _user.getFirstName());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME, _user.getSecondName());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME, _user.getThirdName());
        values.put(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE, _user.getBirthDate());

        db.insert(MedDBContract.UsersContract.TABLE_NAME, null, values);
        db.close();
    }

    public Users getUser(String userName){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = { MedDBContract.UsersContract.COLUMN_NAME_ID,
                MedDBContract.UsersContract.COLUMN_NAME_First_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE};

        Cursor cursor = db.query(MedDBContract.UsersContract.TABLE_NAME, projection, MedDBContract.UsersContract.COLUMN_NAME_First_NAME,
                new String[]{userName}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Users user = new Users(userName);
        user.setID(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_ID)));
        user.setSecondName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME)));
        user.setThirdName(cursor.getString(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME)));
        user.setBirthDate(cursor.getInt(cursor.getColumnIndexOrThrow(MedDBContract.UsersContract.COLUMN_NAME_BIRTH_DATE)));

        cursor.close();
        return users;
    }

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

    public void addPrescription(Prescription _presc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(MedDBContract.PrescriptionContract._ID, _presc.getID());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR, _presc.getDoctor());
        values.put(MedDBContract.PrescriptionContract.COLUMN_NAME_DATE, _presc.getExpiration().getTimeInMillis());

        db.insert(MedDBContract.PrescriptionContract.TABLE_NAME, null, values);
        db.close();
    }

    public void addMedToPresc(Prescription _presc, Medicine _med) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedDBContract.MedPrescContract.COLUMN_NAME_PRESC_ID, _presc.getID());
        values.put(MedDBContract.MedPrescContract.COLUMN_NAME_MED_ID, _med.getID());

        db.insert(MedDBContract.MedPrescContract.TABLE_NAME, null, values);
        db.close();
    }

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

    public Date loadDate(Cursor cursor, int index) {
        if (cursor != null) {
            return new Date(cursor.getLong(index));
        }
        return null;
    }

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

    // Proceso de fetch de medicinas muy lento??
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
    public Cursor getAllPrescriptions() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {MedDBContract.PrescriptionContract._ID,
                                MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR };
        Cursor cursor = db.query(MedDBContract.PrescriptionContract.TABLE_NAME, projection,
                null, null, null, null, null);
        return cursor;

    }
}

