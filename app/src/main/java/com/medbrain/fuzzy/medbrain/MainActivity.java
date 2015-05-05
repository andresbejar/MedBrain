package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Main Activity: actividad principal del app
 * Muestra interfaz con las recetas validas guardadas en la BD
 *
 * @author Andres Bejarano
 */
public class MainActivity extends ActionBarActivity {

    private static final int CREAR_RECETA_REQUEST = 0;
    private static final String TAG = "MedBrain-App";
    Button crearRecetaBtn;
    MyListFragment fragment;

    /**
     * Metodo ejecutado cuando se crea la actividad
     * @param savedInstanceState Bundle utilizado para reiniciar la instancia
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Entered onCreate Main Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearRecetaBtn = (Button)findViewById(R.id.crearRecetaBtn);
        crearRecetaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Clicked crear Receta");
                Intent intent = new Intent(getApplicationContext(), PrescriptionCreationActivity.class);
                startActivity(intent);
            }
        });
        fragment = new MyListFragment();

    }

    /**
     * Brinca a una actividad que permite ver los usuarios guardados en la base.
     * @param view contexto necesario para escuchar el evento del boton
     */
    public void jumpToUsers(View view){
        Intent intent = new Intent(this, UsersView.class);
        startActivity(intent);
    }

    //YONAN
    public void jumpToContacts(View view){
        Intent intent = new Intent(this, ContactsView.class);
        startActivity(intent);
    }


    /**
     *
     * @param menu Menu creado
     * @return Verdadero si se logra crear el menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *
     * @param item MenuItem que fue seleccionado
     * @return verdadero dependiendo del item del menu que se seleccione
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

    public void jumpToAddAppointment(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }


}
