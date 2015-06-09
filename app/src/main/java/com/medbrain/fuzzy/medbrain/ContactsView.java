package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * Actividad que maneja la vista de los contactos actuales
 * @author Yonan Cano
 */
public class ContactsView extends ActionBarActivity {

    /**
     * Se ejecuta cuando se crea la actividad
     * @param savedInstanceState Bundle con informacion de la actividad
     */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contacts_view);
            /*
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new PlaceholderFragment()).commit();
            }*/
        }

    /**
     * Se ejecuta cuando se crea el menu
     * @param menu creado
     * @return true
     */
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_contacts_view, menu);
            return true;
        }

    /**
     * Se ejecuta cuando se selecciona una opcion del menu
     * @param item seleccionado del menu
     * @return boolean
     */
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        /**
         * A placeholder fragment containing a simple view.
         /
         public static class PlaceholderFragment extends Fragment {

         public PlaceholderFragment() { }

         @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_display_message,
         container, false);
         return rootView;
         }
         }*/

    /**
     * Redirige al usuario a la actividad de agregar un nuevo contacto
     * @param view que llama al metodo
     */
    public void jumpToAddContact(View view){
        Intent intent = new Intent(this, AddNewUser.class);
        startActivity(intent);
    }
}