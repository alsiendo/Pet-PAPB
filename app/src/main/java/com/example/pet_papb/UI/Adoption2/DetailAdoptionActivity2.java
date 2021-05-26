package com.example.pet_papb.UI.Adoption2;
//

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pet_papb.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class DetailAdoptionActivity2 extends AppCompatActivity {

    private ImageView gambarHewan;
    private TextView namaHewan;
    private TextView deskripsiHewan;
    private TextView alamatHewan;
    private TextView kategoriHewan;
    private TextView umurHewan;
    private Button approved;

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
        approved = findViewById(R.id.btContactOrApproved);

        Intent intent = getIntent();

        String mNamaHewan = intent.getStringExtra("namaHewan");
        String mAlamatHewan = intent.getStringExtra("alamatHewan");
        String mGambarHewan = intent.getStringExtra("gambarHewan");
        String mDeskripsiHewan = intent.getStringExtra("deskripsiHewan");
        String mKategoriHewan = intent.getStringExtra("kategoriHewan");
        String mUmurHewan = intent.getStringExtra("umurHewan");

        namaHewan.setText(mNamaHewan);
        alamatHewan.setText(mAlamatHewan);
        deskripsiHewan.setText(mDeskripsiHewan);
        kategoriHewan.setText(mKategoriHewan);
        umurHewan.setText(mUmurHewan);

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