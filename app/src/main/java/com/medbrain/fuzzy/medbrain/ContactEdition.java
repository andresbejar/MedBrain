package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by yonancano on 6/7/15.
 */
public class ContactEdition extends ActionBarActivity{
    private static final String TAG = "MedBrain-App";


    private int contactId;
    private DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edition);

        Intent info = getIntent();
        dbHandler = new DatabaseHandler(this);
        Contact contactoAct;

        contactId = info.getIntExtra("id", 0);
        contactoAct = dbHandler.getContactById(contactId);

        //se llenan los campos con un preview de la informacion actual
        EditText nameT, phone1T, phone2T, emailT, especT;

        nameT = (EditText)findViewById(R.id.editText1);
        phone1T = (EditText)findViewById(R.id.editText2);
        phone2T = (EditText)findViewById(R.id.editText3);
        emailT = (EditText)findViewById(R.id.editText4);
        especT = (EditText)findViewById(R.id.editText5);

        //nuevos cambios
        nameT.setText(contactoAct.getName());
        phone1T.setText(contactoAct.getPhone1());
        phone2T.setText(contactoAct.getPhone2());
        emailT.setText(contactoAct.getEmail());
        especT.setText(contactoAct.getEspecialidad());
    }

    public void saveChanges(View view){

        EditText nameT, phone1T, phone2T, emailT, especT;
        nameT = (EditText)findViewById(R.id.editText1);
        phone1T = (EditText)findViewById(R.id.editText2);
        phone2T = (EditText)findViewById(R.id.editText3);
        emailT = (EditText)findViewById(R.id.editText4);
        especT = (EditText)findViewById(R.id.editText5);

        String uname = nameT.getText().toString();
        String uphone1 = phone1T.getText().toString();
        String uphone2 = phone2T.getText().toString();
        String uemail = emailT.getText().toString();
        String uespec = especT.getText().toString();

        dbHandler.updateContact(contactId, uname, uphone1, uphone2, uemail, uespec);

        Intent intent = new Intent(this, ContactsView.class);
        startActivity(intent);
        //startActivity(intentCV);
    }

    public void addPhoto2(View view){
        //Intent intent = new Intent(this, AddNewContact.class);
        //Toast.makeText(getActivity(), "Elegir la Foto", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Agregar la Fotoe");
        //startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
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
