package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddNewUser extends ActionBarActivity {
    private DatabaseHandler dbHandler;

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
        EditText Bdate = (EditText) findViewById(R.id.editText4);

        String Name = name.getText().toString();
        String LastName = lastName.getText().toString();
        String LastName2 = lastName2.getText().toString();
        int ID = Integer.parseInt(id.getText().toString());
        int BDate = Integer.parseInt(Bdate.getText().toString());

        Users user = new Users(Name);
        user.setSecondName(LastName);
        user.setThirdName(LastName2);
        user.setID(ID);
        user.setBirthDate(BDate);

        dbHandler.addUser(user);
        Intent intent = new Intent(this, UsersView.class);
        startActivity(intent);
    }

}
