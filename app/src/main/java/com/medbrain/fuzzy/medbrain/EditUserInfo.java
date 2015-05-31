package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditUserInfo extends ActionBarActivity {

    private int userId;
    private Users editUsr = new Users();
    private DatabaseHandler dbHandler;
    private EditText firstName = (EditText)findViewById(R.id.editText16);
    private EditText secondName = (EditText)findViewById(R.id.editText17);
    private EditText thirdName = (EditText)findViewById(R.id.editText18);
    private EditText ID = (EditText)findViewById(R.id.editText19);
    private EditText bDate = (EditText)findViewById(R.id.editText20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        Intent info = getIntent();
        dbHandler = new DatabaseHandler(this);
        userId = info.getIntExtra("id", 0);
        editUsr = dbHandler.getUserByID(userId);

//se llenan los campos con un preview de la informacion actual

        firstName.setText(editUsr.getFirstName());
        secondName.setText(editUsr.getSecondName());
        thirdName.setText(editUsr.getThirdName());
        ID.setText(Integer.toString(editUsr.getID()));
        bDate.setText(Integer.toString(editUsr.getBirthDate()));

    }

    public void saveChanges(View view){
        String name = firstName.getText().toString();
        String lastName = secondName.getText().toString();
        String lastName2 = thirdName.getText().toString();
        int iD = Integer.parseInt(ID.getText().toString());
        int birthDate = Integer.parseInt(bDate.getText().toString());

        editUsr.setFirstName(name);
        editUsr.setID(iD);
        editUsr.setSecondName(lastName);
        editUsr.setThirdName(lastName2);
        editUsr.setBirthDate(birthDate);

        Intent intent = new Intent(this, UserInfo.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user_info, menu);
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
