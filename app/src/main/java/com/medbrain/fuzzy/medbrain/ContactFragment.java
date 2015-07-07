package com.medbrain.fuzzy.medbrain;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * * Clase ContactFragment que contienen todas las actividad asociadas al Fragment.
 * Obtiene y despliega todos los contactos de la BD
 * Configura el evento de click en cada contacto individual
 * Created by yonancano on 31/5/15.
 */
public class ContactFragment extends ListFragment {

    private static final String TAG = "MedBrain-App";
    private SimpleCursorAdapter adapter;
    private DatabaseHandler dbHandler;
    private Cursor cursor;


    /**
     * metodo temporal que inserta un contacto en la base
     */
    public void crearContactoPrueba(){
        Contact contpb = new Contact("Juan Valdes");
        contpb.setPhone1("12345678");
        contpb.setPhone2("88887777");
        contpb.setEmail("jvaldez");
        contpb.setEspecialidad("Odontologo");
        dbHandler.addContact(contpb);
    }


    /**
     * Se ejecuta al crear la actividad asociada al Fragment
     * Obtiene y despliega todas los contactos en la BD
     * Configura el evento de click en cada contacto individua
     * @param savedInstanceState Bundle con informacion de la Activity
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "Entered onActivityCreated fragment");
        super.onCreate(savedInstanceState);
        dbHandler = new DatabaseHandler(getActivity());

        //crearContactoPrueba();
        //Obtiene y despliega todas los contactos en la BD
        cursor = dbHandler.getAllContacts();
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.cont_item_view,
                cursor, new String[]{MedDBContract.ContactContract._ID,
                MedDBContract.ContactContract.COLUMN_NAME_NAME,
                MedDBContract.ContactContract.COLUMN_NAME_ESPECIALIDAD},
                new int[]{R.id.contID, R.id.nameCont, R.id.especCont}, 0);


        //permite la vista de cada contacto indivudal al presionar el nombre del contacto respectivo
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                TextView contact = (TextView) view.findViewById(R.id.contID);
                Log.i(TAG, "Id del contacto seleccionado: " + contact.getText());
                int contId = Integer.parseInt(contact.getText().toString());
                Intent intent = new Intent(getActivity(), ContactViewActivity.class);
                intent.putExtra("id", contId);
                startActivity(intent);
            }

        });
    }

    /**
     * Metodo ejecutado al crear el view en el que se encuentra el fragment
     * @param inflater LayoutInflater usado para "inflar" el layout del fragment
     * @param container ViewGroup contenedor donde se encontrara el fragment
     * @param savedInstanceState Bundle con informacion de la Activity
     * @return View creado
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(TAG, "Entered onCreateView fragment");
        View view = inflater.inflate(R.layout.list_fragment, container);

        return view;
    }

    public void refresh(){
        Log.i(TAG, "Refreshing cursor");
        adapter.swapCursor(dbHandler.getAllContacts());
    }

    @Override
    public void onResume(){
        Log.i(TAG, "Entered onResume");
        super.onResume();
        refresh();
    }
}
