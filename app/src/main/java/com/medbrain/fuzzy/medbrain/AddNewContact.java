package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Actividad que maneja la creacion de un contacto nuevo
 * @author Yonan Cano
 */
public class AddNewContact extends ActionBarActivity{
    private static final String TAG = "MedBrain-App";
    private DatabaseHandler dbHandler;

    /**
     * Llamado cuando se crea la actividad
     * @param savedInstanceState Bundle con informacion de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        dbHandler = new DatabaseHandler(this);
    }

    /**
     * Llamado cuando se crea el menu
     * @param menu creado
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_contact, menu);
        return true;
    }

    /**
     * Llamado cuando se selecciona una opcion del menu
     * @param item opcion seleccionada
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Crea un nuevo contacto y lo guarda en la BD
     * @param view que llama al metodo
     */
    public void saveContact(View view){ //guarda los datos del contacto en la base
        EditText name = (EditText) findViewById(R.id.editText5);
        EditText phone = (EditText) findViewById(R.id.editText7);
        EditText email = (EditText) findViewById(R.id.editText8);
        EditText espec = (EditText) findViewById(R.id.editText9);
        EditText reput = (EditText) findViewById(R.id.editText10);

        String Name2 = name.getText().toString();
        String Phone2 = phone.getText().toString();
        String Email2 = email.getText().toString();
        String Espec2 = espec.getText().toString();
        String Reput2 = reput.getText().toString();

        Contact cnt = new Contact(Name2);
        cnt.setPhone(Phone2);
        cnt.setEmail(Email2);
        cnt.setEspecialidad(Espec2);
        cnt.setReputacion(Reput2);

        dbHandler.addContact(cnt);
        Intent intent = new Intent(this, ContactsView.class);
        startActivity(intent);
    }

    public void addPhoto(View view){
        //Intent intent = new Intent(this, AddNewContact.class);
        //Toast.makeText(getActivity(), "Elegir la Foto", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Agregar la Foto");
        //startActivity(intent);
    }
}
