package com.medbrain.fuzzy.medbrain;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by andres on 01/05/15.
 */
public class MyListFragment extends ListFragment {

    private static final String TAG = "MedBrain-App";
    private SimpleCursorAdapter adapter;
    private DatabaseHandler dbHandler;
    private Cursor cursor;
    //private ListView listView;

    private void crearRecetaPrueba(){
        Prescription pruebaPresc = new Prescription();
        pruebaPresc.initializeNewPrescription();
        pruebaPresc.setUserID(115150354);
        pruebaPresc.setDoctor("Dr. Rafael Viquez");
        dbHandler.addPrescription(pruebaPresc);
    }

    private void crearAppointmentPrueba(){
        Appointment app = new Appointment();
        app.setName("Prueba1");
        app.setPlace("Somewhiere");
        app.setDoctorID(1234);
        dbHandler.addAppointment(app);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "Entered onActivityCreated fragment");
        super.onCreate(savedInstanceState);

        dbHandler = new DatabaseHandler(getActivity());

        //seccion de prueba-----------------------
        crearRecetaPrueba();
        crearAppointmentPrueba();
        Log.i(TAG, "Added test prescription");
        //fin seccion de prueba-------------------

        cursor = dbHandler.getAllPrescriptions();
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_view,
                cursor, new String[]{MedDBContract.PrescriptionContract._ID,
                MedDBContract.PrescriptionContract.COLUMN_NAME_DOCTOR}, new int[]{R.id.recetaId, R.id.doctor}, 0);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getActivity(), "Clicked prescription # " + position, Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(TAG, "Entered onCreateView fragment");
        View view = inflater.inflate(R.layout.list_fragment, container);

        return view;
    }

}
