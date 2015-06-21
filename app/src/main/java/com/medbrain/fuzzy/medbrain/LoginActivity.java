package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.util.Log;


public class LoginActivity extends ActionBarActivity {

    private Button crearUsuario;
    private DatabaseHandler dbHandler;
    CurrentUser usr = CurrentUser.getInstance();
    private static final String TAG = "MedBrain:Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        // remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        crearUsuario = (Button)findViewById(R.id.mainCrearUsuario);
        dbHandler = new DatabaseHandler(this);
        crearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddNewUser.class);
                Log.i(TAG, "No existe usuario loggeado. Redireccionando a AddNewUser");
                startActivity(intent);
                finish();
            }
        });
        Cursor cursor = dbHandler.getLoggedUser();
        if(cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();
            Log.i(TAG, "Hay un usuario loggeado: redireccionando a MainActivity");
            int usuarioLoggeado = cursor.getInt(0);
            Log.i(TAG, "El usuario loggeado es: " + usuarioLoggeado);
            usr.setCurrentUser(usuarioLoggeado);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
