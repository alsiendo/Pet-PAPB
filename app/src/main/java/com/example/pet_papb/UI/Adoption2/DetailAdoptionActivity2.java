package com.example.pet_papb.UI.Adoption2;
//

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pet_papb.Model.DataAdoption;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Homepage2.Adoption2Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private String statusKepemilikan;
    private Button approved;

    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    @SuppressLint("ResourceAsColor")
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
        statusKepemilikan = intent.getStringExtra("statusKepemilikan");

        namaHewan.setText(mNamaHewan);
        alamatHewan.setText(mAlamatHewan);
        deskripsiHewan.setText(mDeskripsiHewan);
        kategoriHewan.setText(mKategoriHewan);
        umurHewan.setText(mUmurHewan);

        if(statusKepemilikan.equalsIgnoreCase("tersedia")){
            approved.setText("FIND NEW OWNER");
            approved.setBackgroundColor(getResources().getColor(R.color.warna_belum_diambil));
            approved.setTextColor(getResources().getColor(R.color.white));
            approved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ubahStatus();
                }
            });
        }else {
            approved.setText("GOT NEW OWNER");
            approved.setBackgroundColor(getResources().getColor(R.color.warna_diambil));
            approved.setTextColor(getResources().getColor(R.color.white));
        }


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

    private void ubahStatus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Sudah menemukan pemilik baru?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk mengubah status!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        Intent intentlama = getIntent();
                        String mKategoriHewan = intentlama.getStringExtra("kategoriHewan");

                        if(mKategoriHewan.equalsIgnoreCase("Kucing")){
                            databaseReference = FirebaseDatabase.getInstance().getReference("CatAdoptionList");
                        }else if(mKategoriHewan.equalsIgnoreCase("Anjing")){
                            databaseReference = FirebaseDatabase.getInstance().getReference("DogAdoptionList");
                        }else{
                            databaseReference = FirebaseDatabase.getInstance().getReference("RabbitAdoptionList");
                        }

                        databaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                DataAdoption data = snapshot.getValue(DataAdoption.class);
                                Intent intent = getIntent();

                                String mNamaHewan = intent.getStringExtra("namaHewan");
                                String mDeskripsiHewan = intent.getStringExtra("deskripsiHewan");
                                if(data.getNamaHewan().equalsIgnoreCase(mNamaHewan.toString())){
                                    if(data.getDeskripsiHewan().equalsIgnoreCase(mDeskripsiHewan.toString())){
                                        snapshot.getRef().child("statusKepemilikan").setValue("diambil");
                                        dialog.dismiss();
                                    }
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        Intent intent = new Intent(DetailAdoptionActivity2.this, Adoption2Activity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}