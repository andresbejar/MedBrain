package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddNewUser extends ActionBarActivity {
    private DatabaseHandler dbHandler;
    private CurrentUser usr = CurrentUser.getInstance();
    private static final String TAG = "MedBrain:AddNewUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        dbHandler = new DatabaseHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_user, menu);
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
     * guarda los datos de un nuevo usuarios en la base
     * <p>
     *     Toma los valores de los cuadros de texto en la actividad, los onvierte en
     *     el tipo correspondiente, se crea una instancia de la clase Usuario y se inicializan sus valores
     *     se pasa esta instancia al data base handler que guarda los valores en la tabla. Y se devuelve a
     *     su actividad padre.
     * @param view contexto necesario para recibir el evento del boton CREAR
     */
    public void saveData(View view){ //guarda los datos en la base
        EditText name = (EditText) findViewById(R.id.editText);
        EditText lastName = (EditText) findViewById(R.id.editText2);
        EditText lastName2 = (EditText) findViewById(R.id.editText3);
        EditText id = (EditText) findViewById(R.id.editText6);
        EditText day = (EditText) findViewById(R.id.editText4);
        EditText month = (EditText) findViewById(R.id.editText11);
        EditText year = (EditText) findViewById(R.id.editText12);

        String Name = name.getText().toString();
        Name += " ";
        String LastName = lastName.getText().toString();
        LastName += " ";
        String LastName2 = lastName2.getText().toString();
        LastName2 += " ";
        int ID = Integer.parseInt(id.getText().toString());
        String bDay = day.getText().toString();
        String bMonth = month.getText().toString();
        String bYear = year.getText().toString();

        Users user = new Users(Name);
        user.setSecondName(LastName);
        user.setThirdName(LastName2);
        user.setID(ID);
        user.setBirthDay(bDay);
        user.setBirthMonth(bMonth);
        user.setBirthYear(bYear);
        dbHandler.addUser(user);

        //aqui se cambia el usuario loggeado por el recien creado
        if(user.getID() == 0){ //si no hay usuario loggeado
            dbHandler.updateNewLog(ID);
        }
        else{
            dbHandler.updateLoggedUser(user.getID(), ID);
        }

        Log.i(TAG, "Loggeando al usuario " + ID);
        usr.setCurrentUser(ID);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
