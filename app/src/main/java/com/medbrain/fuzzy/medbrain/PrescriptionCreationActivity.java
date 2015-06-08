package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Actividad de creacion de recetas
 * @author Andres Bejarano
 */
public class PrescriptionCreationActivity extends ActionBarActivity {

    private Button limpiarBtn;
    private Button guardarBtn;
    private Button addMedBtn;
    private EditText inputDoctor;
    private ListView medListView;
    private Prescription newPresc;
    private DatabaseHandler dbHandler;
    private static final int ADD_MED_REQ = 0;
    private ArrayList<Medicine> medList = new ArrayList<Medicine>();
    private ArrayAdapter<Medicine> adapter;
    private static final String TAG = "MedBrain-App";


    /**
     * Se ejecuta al crear la actividad
     * @param savedInstanceState Bundle con informacion de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_creation);
        dbHandler = new DatabaseHandler(this);

        //se obtienen los view items
        limpiarBtn = (Button)findViewById(R.id.clearBtn);
        guardarBtn = (Button)findViewById(R.id.saveBtn);
        addMedBtn = (Button)findViewById(R.id.addMedBtn);
        inputDoctor = (EditText)findViewById(R.id.editDoctor);
        medListView = (ListView)findViewById(R.id.listView);
        //se inicializa el adapter de medicinas!
        adapter = new ArrayAdapter<Medicine>(this, android.R.layout.simple_list_item_1, medList);
        medListView.setAdapter(adapter);//BANG!

        newPresc = new Prescription();
        newPresc.initializeNewPrescription();
        //temporal, eliminar!
        limpiarBtn.setOnClickListener(new OnClickListener() {
            //TODO: limpiar los campos de texto
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Por implementar...", Toast.LENGTH_SHORT).show();
            }
        });
        guardarBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                newPresc.setDoctor(inputDoctor.getText().toString());
                dbHandler.addPrescription(newPresc);

                Log.i(TAG, "Added new prescription succesfully");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        addMedBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MedSelectionActivity.class);

                //OJO: Llama a MedSelectionActivity y debe recibir la medicina que el usr selecciono
                Log.i(TAG, "Calling MedSelectionActivity");
                startActivityForResult(intent, ADD_MED_REQ);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ADD_MED_REQ){
            if(resultCode == RESULT_OK){
                Medicine med = data.getParcelableExtra("medicine");
                newPresc.addMedicine(med.getID());
                adapter.add(med);

                Log.i(TAG, "Received and added new medicine");
            }
        }
    }


    /**
     * Se ejecuta al crear el menu
     * @param menu creado
     * @return verdadero
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prescription_creation, menu);
        return true;
    }

    /**
     * Se ejecuta al seleccionar una opcion del menu
     * @param item MenuItem seleccionado
     * @return verdadero
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
}
