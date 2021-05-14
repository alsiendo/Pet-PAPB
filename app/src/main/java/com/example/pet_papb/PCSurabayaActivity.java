package com.example.pet_papb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class PCSurabayaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button backPetCare;
    RecyclerView mRecyclerView;
    MyAdapterPetCare myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcsurabaya);

        //Tombol Back
        backPetCare = (Button) findViewById(R.id.buttonBackPetCareSurabaya);
        backPetCare.setOnClickListener(this);

        //Drop Down Menu
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerPetCareSurabaya);

        ArrayAdapter<String> mySpinnerAdapter = new ArrayAdapter<String>(PCSurabayaActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.petCareSurabaya));

        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(mySpinnerAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    startActivity(new Intent(PCSurabayaActivity.this, PCMalangActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerViewPetCare);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Membuat recyclerview dalam linearlayout

        myAdapter = new MyAdapterPetCare(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBackPetCareSurabaya:
                //Ganti jadi halaman homepage
                startActivity(new Intent(this, DummyActivity.class));
                break;

        }
    }

    private ArrayList<ModelPetCare> getMyList(){
        ArrayList<ModelPetCare> models = new ArrayList<>();
        ModelPetCare m = new ModelPetCare();
        m.setTitle("Surabaya Animal Clinic");
        m.setDescription("Surabaya");
        // Masukkan gambar kedalam drawable folder
        m.setImg(R.drawable.sbyanimalclinic);
        models.add(m);

        m = new ModelPetCare();
        m.setTitle("INI Veterinary Services");
        m.setDescription("Surabaya");
        // Masukkan gambar kedalam drawable folder
        m.setImg(R.drawable.inivetenaryservice);
        models.add(m);


        return models;
    }
}