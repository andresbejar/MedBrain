package com.medbrain.fuzzy.medbrain;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineCreationFragment extends Fragment {

    private static final String TAG = "MedBrain-App";

    private Medicine newMed;
    private DatabaseHandler dbHandler;
    private SelectionListener mCallback;
    private EditText medName;
    private EditText medDose;
    private EditText medDetails;
    private Button acceptBtn;
    private Button cancelBtn;


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        medName = (EditText)getView().findViewById(R.id.editMedNombre);
        medDose = (EditText)getView().findViewById(R.id.editMedDose);
        medDetails = (EditText)getView().findViewById(R.id.editMedDetails);
        acceptBtn = (Button)getView().findViewById(R.id.aceptarBtn);
        cancelBtn = (Button)getView().findViewById(R.id.cancelarBtn);

        //button events
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.switchFragments();
            }
        });

        //db initialization
        dbHandler = new DatabaseHandler(getActivity());
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = medName.getText().toString();
                String dose = medDose.getText().toString();
                String details =  medDetails.getText().toString();
                newMed = new Medicine(name);
                newMed.setDose(dose);
                newMed.setDetails(details);

                newMed.initializeNewMedicine();
                dbHandler.addMedicine(newMed);

                //final callback
                mCallback.onItemSelected(newMed);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_creation, null);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (SelectionListener)activity;
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
