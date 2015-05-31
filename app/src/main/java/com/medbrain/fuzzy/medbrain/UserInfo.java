package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
//import android.view.View;



public class UserInfo extends ActionBarActivity {
    private static final String TAG = "MedBrain-App";

    private int userId;
    private Users usrSelected = new Users();
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Intent info = getIntent();
        dbHandler = new DatabaseHandler(this);
        TextView name = (TextView) findViewById(R.id.textView5);
        TextView cedula = (TextView) findViewById(R.id.textView7);
        TextView bDate = (TextView) findViewById(R.id.textView9);
        userId = info.getIntExtra("id", 0);
        Log.i(TAG, "intent recibido");
        //se recupera el user que corresponde al ID y se llena la pantalla
        usrSelected = dbHandler.getUserByID(userId);

        name.setText(usrSelected.getFirstName() + " " + usrSelected.getSecondName() + " " + usrSelected.getThirdName());
        cedula.setText(Integer.toString(usrSelected.getID()));
        bDate.setText(Integer.toString(usrSelected.getBirthDate()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_info, menu);
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
}
