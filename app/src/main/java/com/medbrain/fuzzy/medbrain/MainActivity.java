package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Main Activity: actividad principal del app
 * Muestra interfaz con las recetas validas guardadas en la BD
 *
 * @author Andres Bejarano
 */
public class MainActivity extends ActionBarActivity {

    private static final int CREAR_RECETA_REQUEST = 0;
    private static final String TAG = "MedBrain-App";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private Button crearRecetaBtn;
    private MyListFragment fragment;
    private String[] navOptions;

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

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mTitle = mDrawerTitle = getTitle();
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        navOptions = getResources().getStringArray(R.array.nav_options);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navOptions));
        mDrawerLayout.setDrawerShadow(R.drawable.ic_drawer_shadow, GravityCompat.START);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open_drawer, R.string.close_drawer){
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        crearRecetaBtn = (Button)findViewById(R.id.crearRecetaBtn);
        crearRecetaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Clicked crear Receta");
                Intent intent = new Intent(getApplicationContext(), PrescriptionCreationActivity.class);
                startActivityForResult(intent, CREAR_RECETA_REQUEST);
            }
        });
        fragment = new MyListFragment();
        setTitle(navOptions[0]);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CREAR_RECETA_REQUEST){
            if(resultCode == RESULT_OK){
                Log.i(TAG, "New Prescription created succesfully");
            }
        }
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

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position){
        Intent intent;
        switch(position){

            case 0:
                Log.i(TAG, "Clicked Receta");
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case 1:
                Log.i(TAG, "Clicked contacts");
                intent = new Intent(getApplicationContext(), ContactsView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case 2:
                Log.i(TAG, "Clicked calendar");
                intent = new Intent(getApplicationContext(), CalendarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case 3:
                Log.i(TAG, "Clicked Users");
                intent = new Intent(getApplicationContext(), UsersView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            default:
                Log.i(TAG, "Error");
        }
        mDrawerList.setItemChecked(position, true);
        setTitle(navOptions[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}

