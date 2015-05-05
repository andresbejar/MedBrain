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
 * Created by Ulises on 5/5/2015.
 */
public class UserFragment extends ListFragment {
    private static final String TAG = "MedBrain-App";
    private SimpleCursorAdapter adapter;
    private DatabaseHandler dbHandler;
    private Cursor cursor;
    //private ListView listView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "Entered onActivityCreated fragment");
        super.onCreate(savedInstanceState);

        dbHandler = new DatabaseHandler(getActivity());

        cursor = dbHandler.getAllUsers();
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_view,
                cursor, new String[]{"rowid _id", MedDBContract.UsersContract.COLUMN_NAME_FIRST_NAME,
                MedDBContract.UsersContract.COLUMN_NAME_SECOND_NAME, MedDBContract.UsersContract.COLUMN_NAME_THIRD_NAME}, new int[]{R.id.firstName, R.id.secondName, R.id.thirdName}, 0);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getActivity(), "Clicked User # " + position, Toast.LENGTH_SHORT).show();

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
