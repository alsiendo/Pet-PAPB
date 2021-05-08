package com.example.pet_papb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PetCareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_care);

        //Drop Down Menu
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerPetCare);

        ArrayAdapter<String> mySpinnerAdapter = new ArrayAdapter<String>(PetCareActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.petCareCities));

        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(mySpinnerAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    startActivity(new Intent(PetCareActivity.this, PCMalangActivity.class));
                }else if (i == 2){
                    startActivity(new Intent(PetCareActivity.this, PCSurabayaActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}