package com.example.pet_papb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PCMalangTwoDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTitlePetCare, tvAlamatPetCare, tvJamPetCare, tvTeleponPetCare, tvProvinsiPetCare;
    private ImageView ivVetPetCare;
    private Button backPetCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcmalang_two_detail);
        ivVetPetCare = (ImageView) findViewById(R.id.imageViewDetailPetCare);
        ivVetPetCare.setImageResource(R.drawable.altavetclinic);

        tvTitlePetCare = (TextView) findViewById(R.id.textViewTitlePetCare);
        tvTitlePetCare.setText("Alta Vet Clinic");

        tvAlamatPetCare = (TextView) findViewById(R.id.textViewAlamatPetCare);
        tvAlamatPetCare.setText("Jl. Raya Sengkaling No.102, Jetis, Mulyoagung, Kec. Dau, Malang, Jawa Timur 65151");

        tvJamPetCare = (TextView) findViewById(R.id.textViewJamPetCare);
        tvJamPetCare.setText("Buka pukul 09.00 - 17.00 WIB");

        tvTeleponPetCare = (TextView) findViewById(R.id.textViewTeleponPetCare);
        tvTeleponPetCare.setText("0812-1667-8543");

        tvProvinsiPetCare = (TextView) findViewById(R.id.textViewProvinsiPetCare);
        tvProvinsiPetCare.setText("Jawa Timur");


        //Tombol Back
        backPetCare = (Button) findViewById(R.id.buttonBackPetCareDetail);
        backPetCare.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBackPetCareDetail:
                //Ganti jadi halaman homepage
                startActivity(new Intent(this, PCMalangActivity.class));
                break;

        }
    }
}