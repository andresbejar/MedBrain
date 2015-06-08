package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class UsersView extends ActionBarActivity {
    private static final String TAG = "MedBrain-App";

    //------LAYOUT ITEMS DEL DRAWER, NO BORRAR--------------------------------------------
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navOptions;
    //---------------------------------------------------------------------------------------

    UserFragment fragment;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_view);

        //----SECCION DE CONFIG DEL DRAWER----------------------------------------------------------
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mTitle = mDrawerTitle = getTitle();
        mDrawerTitle = "MedBrain";
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
        setTitle(navOptions[3]);
        //------------------------------------------------------------------------------------------
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_users_view, menu);
        fragment = new UserFragment();
        dbHandler = new DatabaseHandler(this);
        return true;
    }

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

    /**
     *cambia de actividad
     *<p>
     *     Se cambia a una actividad que permite agregar usuarios
     *<p>
     * @param view contexto necesario para que el boton pueda llamar al metodo.
     */
    public void jumpToAddUser(View view){
        Intent intent = new Intent(this, AddNewUser.class);
        startActivity(intent);
    }

    //-------MAS CONFIG DEL DRAWER------------------------------------------------------------------
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
    //----------------------------------------------------------------------------------------------
}
