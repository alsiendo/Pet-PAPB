package com.example.pet_papb.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pet_papb.UI.Login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import com.example.pet_papb.R;

public class DummyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dummyPetCare, logout;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        dummyPetCare = (Button) findViewById(R.id.dummyBtnPetCare);
        dummyPetCare.setOnClickListener(this);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dummyBtnPetCare:
                //Ganti dari halaman homepage
                startActivity(new Intent(this, PetCareActivity.class));
                break;
            case R.id.btnLogout:
                mAuth.signOut();
                startActivity(new Intent(DummyActivity.this, LoginActivity.class));

        }
    }
}