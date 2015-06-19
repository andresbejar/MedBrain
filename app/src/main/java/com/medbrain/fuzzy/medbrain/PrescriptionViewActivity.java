package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;


public class PrescriptionViewActivity extends ActionBarActivity {

    //TODO: AGREGARLE BOTON DE BORRAR RECETA
    private static final String TAG = "MedBrain-App";

    private TextView prescTitle;
    private TextView doctor;
    private ListView medicinas;
    private Button deleteBtn;
    private Prescription currPresc;;

    private int prescId;
    private DatabaseHandler dbHandler;
    private ArrayList<Medicine> medicineArray;
    private ArrayAdapter<Medicine> medicineArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_view);

        Intent info = getIntent();
        dbHandler = new DatabaseHandler(this);
        medicineArray = new ArrayList<Medicine>();

        prescTitle = (TextView)findViewById(R.id.recetaTextView);
        doctor = (TextView)findViewById(R.id.doctorTextView);
        medicinas = (ListView)findViewById(R.id.medicinasListView);
        deleteBtn = (Button)findViewById(R.id.borrarRecetaBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deletePrescription(prescId);
                Log.i(TAG, "Deleted prescription and now finishing activity...");
                finish();
            }
        });

        prescId = info.getIntExtra("id", 0);
        Log.i(TAG, "ID de la receta recibido: " + prescId);

        //ahora hago el fetch del prescription entero desde BD
        currPresc = dbHandler.getPrescription(prescId);
        fetchMedicines(currPresc);

        //setup del adapter
        medicineArrayAdapter = new ArrayAdapter<Medicine>(this, android.R.layout.simple_list_item_1, medicineArray);
        medicinas.setAdapter(medicineArrayAdapter);

        medicinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Mostrar info de calendarizacion...", Toast.LENGTH_SHORT).show();
            }
        });

        //termino setup del id de la receta y el doctor
        prescTitle.setText(currPresc.getMotivo());
        doctor.setText(currPresc.getDoctor());
        setTitle(prescTitle.getText());

    }

    public void fetchMedicines(Prescription presc){
        for (int id : presc.Medicines){
            Log.i(TAG, "Adding medicine ID: " + id);
            medicineArray.add(dbHandler.getMedicine(id));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prescription_view, menu);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
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
