package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Yonan on 05/05/2015.
 */
public class AddNewContact extends ActionBarActivity{
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_contact, menu);
        return true;
    }

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

    public void saveContact(View view){ //guarda los datos del contacto en la base
        EditText name = (EditText) findViewById(R.id.editText5);
        EditText phone = (EditText) findViewById(R.id.editText7);
        EditText email = (EditText) findViewById(R.id.editText8);
        EditText espec = (EditText) findViewById(R.id.editText9);
        EditText reput = (EditText) findViewById(R.id.editText10);
        EditText precio = (EditText) findViewById(R.id.editText11);

        String Name2 = name.getText().toString();
        String Phone2 = phone.getText().toString();
        String Email2 = email.getText().toString();
        String Espec2 = espec.getText().toString();
        String Reput2 = reput.getText().toString();
        String Precio2 = precio.getText().toString();

        Contact cnt = new Contact(Name2);
        cnt.setPhone(Phone2);
        cnt.setEmail(Email2);
        cnt.setEspecialidad(Espec2);
        cnt.setReputacion(Reput2);
        cnt.setPrecio(Precio2);

        dbHandler.addContact(cnt);
        /*Intent intent = new Intent(this, AddNewUser.class);
        startActivity(intent);*/
    }
}
