package com.medbrain.fuzzy.medbrain;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AddNewAppointment extends ActionBarActivity {
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_appointment);
        dbHandler = new DatabaseHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_appointment, menu);
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
        EditText name = (EditText) findViewById(R.id.editText16);
        EditText place = (EditText) findViewById(R.id.editText17);
        EditText doctor = (EditText) findViewById(R.id.editText18);
        DatePicker date = (DatePicker) findViewById(R.id.datePicker3);
        TimePicker time = (TimePicker) findViewById(R.id.timePicker3);
        NumberPicker hours = (NumberPicker) findViewById(R.id.numberPicker);

        Calendar newCal = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDayOfMonth(), time.getCurrentHour(), time.getCurrentMinute());

        String newName = name.getText().toString();
        String newPlace = place.getText().toString();
        String newDoctor = doctor.getText().toString();
        int newHours = hours.getValue();

        Appointment app = new Appointment();
        app.setName(newName);
        app.setPlace(newPlace);
        app.setDoctorID(newDoctor);
        app.setInnerCalendar(newCal.getTimeInMillis());

        dbHandler.addAppointment(app);
        remindAppointment(newName, newCal.getTimeInMillis(), newHours);
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void remindAppointment(String appName, long timeInMills, int hoursBefore){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notID = Integer.valueOf(last4Str);

        Intent broadcast_intent = new Intent(this, appNotificationService.class);

        broadcast_intent.setData(Uri.parse("timer:" + notID));
        broadcast_intent.putExtra("string_value", appName); //data to pass
        broadcast_intent.putExtra("long_value", timeInMills);
        broadcast_intent.putExtra("notification_id", notID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, broadcast_intent, 0);

        int hoursToRemind = hoursBefore * 3600000;

        alarmManager.set(AlarmManager.RTC_WAKEUP, (timeInMills - hoursToRemind), pendingIntent);
    }
}

