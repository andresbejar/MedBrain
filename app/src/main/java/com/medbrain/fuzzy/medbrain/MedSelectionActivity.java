package com.medbrain.fuzzy.medbrain;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;


public class MedSelectionActivity extends ActionBarActivity implements SelectionListener {

    private MedicineListFragment medFrag;
    private MedicineCreationFragment medCreateFrag;
    private static final int ADD_MED_REQ = 0;
    private static final String TAG = "MedBrain-App";
    private Medicine selectedMed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_selection);
        Intent intent = getIntent();

        medFrag = new MedicineListFragment();

        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTran = fragMan.beginTransaction();
        fragTran.add(R.id.fragment_container, medFrag);
        fragTran.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_med_selection, menu);
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

    public void onItemSelected(Medicine med){
        Intent rmdrMed = new Intent(this, AddNewRmdr.class);
        rmdrMed.putExtra("medicine", med);
        selectedMed = med;
        startActivityForResult(rmdrMed, ADD_MED_REQ);
        Log.i(TAG, "Medicine selected. Calling AddNewRmdr....");

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ADD_MED_REQ){
            if(resultCode == RESULT_OK){
                Log.i(TAG, "Se logro calendarizar la medicina");
                Intent intent = new Intent();
                intent.putExtra("medicine", selectedMed);

                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }


    public void switchFragments(){
        Log.i(TAG, "Switching frags");

        if(medCreateFrag == null){
            medCreateFrag = new MedicineCreationFragment();
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, medCreateFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        // execute transaction now
        getFragmentManager().executePendingTransactions();
    }

}
