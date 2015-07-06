package com.medbrain.fuzzy.medbrain;

import android.annotation.TargetApi;
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
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AddNewRmdr extends ActionBarActivity {
    private DatabaseHandler dbHandler;

    EditText name;
    DatePicker date;
    TimePicker time;
    NumberPicker hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_rmdr);
        dbHandler = new DatabaseHandler(this);

        name = (EditText) findViewById(R.id.editText15);
        date = (DatePicker) findViewById(R.id.datePicker2);
        time = (TimePicker) findViewById(R.id.timePicker2);
        hours = (NumberPicker) findViewById(R.id.numberPicker2);

        Intent medIntent = getIntent();
        String cleanName = "";
        Medicine existingMedicine = medIntent.getParcelableExtra("medicine");

        if(existingMedicine != null){
            cleanName += existingMedicine.getName() + ", " + existingMedicine.getDose();
            name.setText(cleanName, TextView.BufferType.EDITABLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_rmdr, menu);
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

    @TargetApi(11)
    public void saveData(View view){





        Calendar newCal = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDayOfMonth(), time.getCurrentHour(), time.getCurrentMinute());
        String newName = name.getText().toString();
        int newHours = hours.getValue();

        Reminder rmdr = new Reminder();
        rmdr.setName(newName);
        rmdr.setInnerCalendar(newCal.getTimeInMillis());

        dbHandler.addReminder(rmdr);
        remindReminder(newName, newCal.getTimeInMillis(), newHours);
        setResult(RESULT_OK, null);
        finish();
        //Intent intent = new Intent(this, CalendarActivity.class);
        //startActivity(intent);
    }

    private void remindReminder(String rmdrName, long timeInMills, int hoursBefore){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notID = Integer.valueOf(last4Str);

        Intent broadcast_intent = new Intent(this, appNotificationService.class);

        broadcast_intent.setData(Uri.parse("timer:" + notID));
        broadcast_intent.putExtra("string_value", rmdrName); //data to pass
        broadcast_intent.putExtra("long_value", timeInMills);
        broadcast_intent.putExtra("notification_id", notID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, broadcast_intent, 0);

        int hoursToRemind = hoursBefore * 3600000;

        alarmManager.set(AlarmManager.RTC_WAKEUP, (timeInMills - hoursToRemind), pendingIntent);
    }
}
