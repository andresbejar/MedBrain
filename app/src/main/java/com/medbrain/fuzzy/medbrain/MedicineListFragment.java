package com.medbrain.fuzzy.medbrain;

import android.app.Activity;
import android.app.ListFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.util.Log;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by andres on 04/05/15.
 */
public class MedicineListFragment extends ListFragment {
    private String TAG = "MedBrain-App";

    private SimpleCursorAdapter adapter;
    private DatabaseHandler dbHandler;
    private Cursor cursor;
    private Button agregarMedBtn;
    public Medicine med;



    private SelectionListener mCallback;


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        agregarMedBtn = (Button)getView().findViewById(R.id.addMedBtn);
        agregarMedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.switchFragments();

            }
        });

        dbHandler = new DatabaseHandler(getActivity());
        cursor = dbHandler.getAllMeds();
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.med_item_view, cursor,
                new String[] {MedDBContract.MedicineContract.COLUMN_NAME_TITLE,
                        MedDBContract.MedicineContract.COLUMN_NAME_DOSE,
                        MedDBContract.MedicineContract._ID}, new int[]{
                R.id.med_name, R.id.med_dose, R.id.med_id}, 0);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView medName = (TextView)view.findViewById(R.id.med_name);
                TextView medId = (TextView)view.findViewById(R.id.med_id);
                TextView medDose = (TextView)view.findViewById(R.id.med_dose);
                med = new Medicine(medName.getText().toString());
                med.setID(Integer.parseInt(medId.getText().toString()));
                med.setDose(medDose.getText().toString());

                //Devuelve el objeto medicina a MedSelectionActivity
                //Luego MedSelectionActivity devuelve la medicina a PrescriptionCreationActivity
                mCallback.onItemSelected(med);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.med_list_fragment, null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mCallback = (SelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SelectionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
