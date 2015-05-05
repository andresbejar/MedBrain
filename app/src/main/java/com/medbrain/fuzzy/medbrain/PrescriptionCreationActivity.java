package com.medbrain.fuzzy.medbrain;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class PrescriptionCreationActivity extends ActionBarActivity {

    private Button limpiarBtn;
    private Button guardarBtn;
    private Button addMedBtn;
    private EditText inputDoctor;
    private ListView medListView;
    private Prescription newPresc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_creation);

        //se obtienen los view items
        limpiarBtn = (Button)findViewById(R.id.clearBtn);
        guardarBtn = (Button)findViewById(R.id.saveBtn);
        addMedBtn = (Button)findViewById(R.id.addMedBtn);
        inputDoctor = (EditText)findViewById(R.id.editDoctor);
        medListView = (ListView)findViewById(R.id.listView);

        //temporal, eliminar!
        limpiarBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Por implementar...", Toast.LENGTH_SHORT).show();
            }
        });
        guardarBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Por implementar...", Toast.LENGTH_SHORT).show();
            }
        });
        addMedBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Por implementar...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prescription_creation, menu);
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
