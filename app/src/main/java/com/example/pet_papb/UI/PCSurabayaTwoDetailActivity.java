package com.example.pet_papb.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pet_papb.R;

public class PCSurabayaTwoDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTitlePetCare, tvAlamatPetCare, tvJamPetCare, tvTeleponPetCare, tvProvinsiPetCare;
    private ImageView ivVetPetCare;
    private Button backPetCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcsurabaya_two_detail);
        ivVetPetCare = (ImageView) findViewById(R.id.imageViewDetailPetCare);
        ivVetPetCare.setImageResource(R.drawable.inivetenaryservice);

        tvTitlePetCare = (TextView) findViewById(R.id.textViewTitlePetCare);
        tvTitlePetCare.setText("INI Veterinary Services");

        tvAlamatPetCare = (TextView) findViewById(R.id.textViewAlamatPetCare);
        tvAlamatPetCare.setText("Rungkut Mejoyo Utara V No.31, Kali Rungkut, Kec. Rungkut, Kota SBY, Jawa Timur 60293");

        tvJamPetCare = (TextView) findViewById(R.id.textViewJamPetCare);
        tvJamPetCare.setText("Buka pukul 08.00 - 19.00 WIB");

        tvTeleponPetCare = (TextView) findViewById(R.id.textViewTeleponPetCare);
        tvTeleponPetCare.setText("(031) 8411810");

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
                startActivity(new Intent(this, PCSurabayaActivity.class));
                break;

        }
    }
}
