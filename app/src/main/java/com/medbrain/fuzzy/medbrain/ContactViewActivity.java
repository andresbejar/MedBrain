package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/** Actividad que permite visualizar un contacto medico
 * Created by yonancano on 31/5/15.
 */
public class ContactViewActivity extends ActionBarActivity{
    private static final String TAG = "MedBrain-App";

    private Contact contactoAct = new Contact();
    private int contId;
    private DatabaseHandler dbHandler;
    private ImageButton deleteButton, editButton;


    /**
     * Actividad que permite la recuperacion del contacto especificado por el USR
     * Identifica el contacto seleccionado, y luego despliega ese contacto en una nueva vista
     * @param savedInstanceState Bundle con informacion de la Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        final Intent intentCont = new Intent(this, ContactsView.class);
        Intent info = getIntent();
        dbHandler = new DatabaseHandler(this);

        TextView nombreT,emailT,phone1T,phone2T,especT;
        nombreT = (TextView)findViewById(R.id.textViewNombcont);
        phone1T = (TextView)findViewById(R.id.textViewNoTel1);
        phone2T = (TextView)findViewById(R.id.textViewRep);
        emailT = (TextView)findViewById(R.id.textViewEmail);
        especT = (TextView)findViewById(R.id.textViewEspec);

        //String nomb = info.getStringExtra("id"); //emailT.getText().toString();
        contId = info.getIntExtra("id", 0);
        Log.i(TAG, "ID del contacto recibido: " + contId);

        //ahora se carga el contacto de la BD
        //contactoAct = dbHandler.getContact(nomb);
        contactoAct = dbHandler.getContactById(contId);

        nombreT.setText(contactoAct.getName());
        phone1T.setText(contactoAct.getPhone1());
        phone2T.setText(contactoAct.getPhone2());
        emailT.setText(contactoAct.getEmail());
        especT.setText(contactoAct.getEspecialidad());

        //eliminar contacto
        deleteButton = (ImageButton)findViewById(R.id.imageButtonEliminar);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Eliminar Contacto: " + contId);
                dbHandler.deleteContact(contId);
                Log.i(TAG, "Eliminado Contacto: " + contId);
                startActivity(intentCont);
            }
        });

        //editar contacto
        //editButton = (ImageButton)findViewById(R.id.imageButtonEdit);
        //editButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Log.i(TAG, "Editar Contacto: " + contId);
         //       startActivity(intentCont2);
                //dbHandler.editContact(contId);
        //        Log.i(TAG, "Editado Contacto: " + contId);
                //startActivity(intentCont);
         //   }
        //});
    }

    public void jmpToEdit(View view){
        Intent intent = new Intent(this, ContactEdition.class);
        intent.putExtra("id", contId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_view, menu);
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
     * Elimina el contatco deseado de la base de datos
     * @param view con la vista actual
     */
    /*public void deleteContact(View view){
        ID = (TextView)findViewById(R.id.editText19);
        int idCont = Integer.parseInt(ID.getText().toString());

        dbHandler = new DatabaseHandler(this);
        dbHandler.deleteContact(idCont);
        Log.i(TAG, "Suprimir Contacto: " + idCont);
        Intent intent = new Intent(this, ContactsView.class);
        startActivity(intent);
    }
    */

}