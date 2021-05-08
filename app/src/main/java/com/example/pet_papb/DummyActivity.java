package com.example.pet_papb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DummyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dummyPetCare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        dummyPetCare = (Button) findViewById(R.id.dummyBtnPetCare);
        dummyPetCare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dummyBtnPetCare:
                startActivity(new Intent(this, PetCareActivity.class));
                break;

        }
    }
}