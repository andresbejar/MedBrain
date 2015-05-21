package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddNewApp extends ActionBarActivity {
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_app);
        dbHandler = new DatabaseHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_app, menu);
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

    /**
     * Guarda la información ingresada por el usuario en la pantalla de agregar citas
     * @param view
     * @return void
     */
    public void saveData(View view){ //guarda los datos en la base
        EditText name = (EditText) findViewById(R.id.editText12);
        EditText place = (EditText) findViewById(R.id.editText13);
        EditText doctor = (EditText) findViewById(R.id.editText14);
        EditText date = (EditText) findViewById(R.id.editText15);

        String newName = name.getText().toString();
        String newPlace = place.getText().toString();
        String newDoctor = doctor.getText().toString();
        int newDate = Integer.parseInt(date.getText().toString());

        Appointment app = new Appointment();
        app.setName(newName);
        app.setPlace(newPlace);
        app.setDoctorID(newDoctor);
        app.setInnerCalendar(newDate);

        dbHandler.addAppointment(app);
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
