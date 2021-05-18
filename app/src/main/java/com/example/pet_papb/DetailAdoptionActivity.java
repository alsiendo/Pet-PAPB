package com.example.pet_papb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class DetailAdoptionActivity extends AppCompatActivity {

    private ImageView gambarHewan;
    private TextView namaHewan;
    private TextView deskripsiHewan;
    private TextView alamatHewan;
    private TextView kategoriHewan;
    private TextView umurHewan;
    private Button contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_adoption);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        gambarHewan = findViewById(R.id.ivGambarHewan);
        namaHewan = findViewById(R.id.tvNamaHewan);
        deskripsiHewan = findViewById(R.id.tvDeskripsiHewan);
        alamatHewan = findViewById(R.id.tvAlamatHewan);
        kategoriHewan = findViewById(R.id.tvKategoriHewan);
        umurHewan = findViewById(R.id.tvUmurHewan);
        contact = findViewById(R.id.btContact);

        Intent intent = getIntent();

        String mNamaHewan = intent.getStringExtra("namaHewan");
        String mAlamatHewan = intent.getStringExtra("alamatHewan");
        String mGambarHewan = intent.getStringExtra("gambarHewan");

        namaHewan.setText(mNamaHewan);
        alamatHewan.setText(mAlamatHewan);

        Picasso.get().load(mGambarHewan).networkPolicy(NetworkPolicy.OFFLINE).into(gambarHewan, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(mGambarHewan).into(gambarHewan);
            }
        });
    }
}