package com.medbrain.fuzzy.medbrain;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.SimpleCursorAdapter.ViewBinder;

import java.text.DateFormat;
import java.util.Locale;
import java.util.Date;

/**
 * Created by Julio on 5/5/15.
 */
public class AppsFragment extends ListFragment {
    private static final String TAG = "MedBrain-App";
    private SimpleCursorAdapter adapter;
    private DatabaseHandler dbHandler;
    private Cursor cursor;

    /**
     * Devuelve todas las citas creadas actualmente y las jala desde la base de datos para desplegarlas en forma de lista
     * @param Bundle savedInstanceState
     * @return void
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "Entered onActivityCreated fragment");
        super.onCreate(savedInstanceState);

        dbHandler = new DatabaseHandler(getActivity());

        cursor = dbHandler.getAllAppointments();
        adapter = new SimpleCursorAdapter(getActivity(),
                R.layout.events_item_view,
                cursor,
                new String[]{MedDBContract.AppointmentContract.COLUMN_NAME_NAME, MedDBContract.AppointmentContract.COLUMN_NAME_PLACE, MedDBContract.AppointmentContract.COLUMN_NAME_DATE},
                new int[]{R.id.appNameID, R.id.placeID, R.id.dispDateID},
                0);
        adapter.setViewBinder(new CustomViewBinder());

        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getActivity(), "Clicked Appointment # " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class CustomViewBinder implements ViewBinder {

        @Override
        public boolean setViewValue(View view, Cursor cursor, int i) {
            if (i == 4) {
                TextView textView = (TextView) view;

                int dateStyle = DateFormat.FULL;
                int timeStyle = DateFormat.SHORT;
                Date date = new Date(Long.parseLong(cursor.getString(i)));

                DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.getDefault());
                String formattedDate = df.format(date);
                textView.setText(formattedDate);
                return true;
            }
            return false;

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(TAG, "Entered onCreateView fragment");
        View view = inflater.inflate(R.layout.list_fragment, container);

        return view;
    }

}
