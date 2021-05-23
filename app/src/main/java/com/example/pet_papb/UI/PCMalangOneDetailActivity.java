package com.example.pet_papb.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pet_papb.R;

public class PCMalangOneDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitlePetCare, tvAlamatPetCare, tvJamPetCare, tvTeleponPetCare, tvProvinsiPetCare;
    private ImageView ivVetPetCare;
    private Button backPetCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcmalang_one_detail);

        ivVetPetCare = (ImageView) findViewById(R.id.imageViewDetailPetCare);
        ivVetPetCare.setImageResource(R.drawable.phanteravet);

        tvTitlePetCare = (TextView) findViewById(R.id.textViewTitlePetCare);
        tvTitlePetCare.setText("Panthera Vet");

        tvAlamatPetCare = (TextView) findViewById(R.id.textViewAlamatPetCare);
        tvAlamatPetCare.setText("Jl. Candi Sari II No.3, Mojolangu, Kec. Lowokwaru, Kota Malang, Jawa Timur 65142");

        tvJamPetCare = (TextView) findViewById(R.id.textViewJamPetCare);
        tvJamPetCare.setText("Buka pukul 09.00 - 20.00 WIB");

        tvTeleponPetCare = (TextView) findViewById(R.id.textViewTeleponPetCare);
        tvTeleponPetCare.setText("0856-4914-4583");

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