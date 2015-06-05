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
//import android.widget.Toast;


/**
 * Created by Ulises on 5/5/2015.
 */
public class UserFragment extends ListFragment {
    private static final String TAG = "MedBrain-App";
    private SimpleCursorAdapter adapter;
    private DatabaseHandler dbHandler;
    private Cursor cursor;
    //private ListView listView;

    /**
     * metodo temporal que inserta un usuario en la base
     */
    public void crearUsuarioPrueba(){
        Users user = new Users("Cleto");
        user.setSecondName("Ramirez");
        user.setThirdName("Picado");
        user.setBirthDate(99);
        user.setID(115500425);
        dbHandler.addUser(user);
    }

    /**
     * Se ejecuta al crear la actividad asociada al Fragment
     * Obtiene todos los Usuarios en la BD y se las da al Adapter para que los despliegue
     * Configura el evento de click en cada usuario individual
     * De momento solo se despliega un mensaje informativo
     * @param savedInstanceState informacion en la actividad
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "Entered onActivityCreated fragment");
        super.onCreate(savedInstanceState);

        dbHandler = new DatabaseHandler(getActivity());

        //seccion de prueba-----------------------
        //crearUsuarioPrueba();
        Log.i(TAG, "Added test user");
        //fin seccion de prueba-------------------

        cursor = dbHandler.getAllUsers();
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_view,
                cursor, new String[]{MedDBContract.UsersContract._ID, MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME, MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME},
                new int[]{R.id.userID, R.id.firstName, R.id.secondName, R.id.thirdName}, 0);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                TextView Id = (TextView) view.findViewById(R.id.userID);
                Log.i(TAG, "Id de la receta seleccionada: " + Id.getText());
                int userId = Integer.parseInt(Id.getText().toString());
                Intent intent = new Intent(getActivity(), UserInfo.class);
                intent.putExtra("id", userId);
                Log.i(TAG, "antes de llamar a info");
                startActivity(intent);

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
