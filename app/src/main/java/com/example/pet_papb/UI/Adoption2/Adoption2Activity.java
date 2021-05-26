package com.example.pet_papb.UI.Adoption2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_papb.Model.DataAdoption;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Adoption1.DetailAdoptionActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Adoption2Activity extends AppCompatActivity {

    private RecyclerView catRecycler;
    private RecyclerView dogRecycler;
    private RecyclerView rabbitRecycler;

    private DatabaseReference catAdoptionDatabase;
    private DatabaseReference dogAdoptionDatabase;
    private DatabaseReference rabbitAdoptionDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        catAdoptionDatabase = FirebaseDatabase.getInstance().getReference().child("CatAdoptionList");
        dogAdoptionDatabase = FirebaseDatabase.getInstance().getReference().child("DogAdoptionList");
        rabbitAdoptionDatabase = FirebaseDatabase.getInstance().getReference().child("RabbitAdoptionList");


        // CAT RECYCLER VIEW
        catRecycler = findViewById(R.id.cat_rv);
        LinearLayoutManager lmCat = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lmCat.setReverseLayout(true);
        lmCat.setStackFromEnd(true);
        catRecycler.setHasFixedSize(true);
        catRecycler.setLayoutManager(lmCat);

        // DOG RECYCLER VIEW
        dogRecycler = findViewById(R.id.dog_rv);
        LinearLayoutManager lmDog = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lmDog.setReverseLayout(true);
        lmDog.setStackFromEnd(true);
        dogRecycler.setHasFixedSize(true);
        dogRecycler.setLayoutManager(lmDog);

        // RABBIT RECYCLER VIEW
        rabbitRecycler = findViewById(R.id.rabbit_rv);
        LinearLayoutManager lmRabbit = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lmRabbit.setReverseLayout(true);
        lmRabbit.setStackFromEnd(true);
        rabbitRecycler.setHasFixedSize(true);
        rabbitRecycler.setLayoutManager(lmRabbit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query queryCat = catAdoptionDatabase.orderByChild("statusKepemilikan").equalTo("tersedia");
        Query queryDog = dogAdoptionDatabase.orderByChild("statusKepemilikan").equalTo("tersedia");
        Query queryRabbit = rabbitAdoptionDatabase.orderByChild("statusKepemilikan").equalTo("tersedia");

        FirebaseRecyclerAdapter<DataAdoption, CatViewHolder> adapterCat = new FirebaseRecyclerAdapter<DataAdoption, CatViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption,
                        CatViewHolder.class,
                        queryCat
                ) {
            @Override
            protected void populateViewHolder(CatViewHolder catViewHolder, DataAdoption dataAdoption, int i) {
                catViewHolder.setNama(dataAdoption.getNamaHewan());
                catViewHolder.setAlamat(dataAdoption.getLokasiHewan());
                catViewHolder.setGambar(dataAdoption.getGambarHewan());

                catViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Adoption2Activity.this, DetailAdoptionActivity.class);
                        intent.putExtra("namaHewan", dataAdoption.getNamaHewan());
                        intent.putExtra("alamatHewan", dataAdoption.getLokasiHewan());
                        intent.putExtra("gambarHewan", dataAdoption.getGambarHewan());
                        intent.putExtra("deskripsiHewan", dataAdoption.getDeskripsiHewan());
                        intent.putExtra("kategoriHewan", dataAdoption.getJenisHewan());
                        intent.putExtra("umurHewan", dataAdoption.getUmurHewan());
                        startActivity(intent);
                    }
                });
            }
        };

        catRecycler.setAdapter(adapterCat);

        FirebaseRecyclerAdapter<DataAdoption, DogViewHolder> adapterDog = new FirebaseRecyclerAdapter<DataAdoption, DogViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption,
                        DogViewHolder.class,
                        queryDog
                ) {
            @Override
            protected void populateViewHolder(DogViewHolder dogViewHolder, DataAdoption dataAdoption, int i) {
                dogViewHolder.setNama(dataAdoption.getNamaHewan());
                dogViewHolder.setAlamat(dataAdoption.getLokasiHewan());
                dogViewHolder.setGambar(dataAdoption.getGambarHewan());

                dogViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Adoption2Activity.this, DetailAdoptionActivity.class);

                        intent.putExtra("namaHewan", dataAdoption.getNamaHewan());
                        intent.putExtra("alamatHewan", dataAdoption.getLokasiHewan());
                        intent.putExtra("gambarHewan", dataAdoption.getGambarHewan());
                        intent.putExtra("deskripsiHewan", dataAdoption.getDeskripsiHewan());
                        intent.putExtra("kategoriHewan", dataAdoption.getJenisHewan());
                        intent.putExtra("umurHewan", dataAdoption.getUmurHewan());
                        startActivity(intent);
                    }
                });
            }
        };

        dogRecycler.setAdapter(adapterDog);

        FirebaseRecyclerAdapter<DataAdoption, RabbitViewHolder> adapterRabbit = new FirebaseRecyclerAdapter<DataAdoption, RabbitViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption,
                        RabbitViewHolder.class,
                        queryRabbit
                ) {
            @Override
            protected void populateViewHolder(RabbitViewHolder rabbitViewHolder, DataAdoption dataAdoption, int i) {
                rabbitViewHolder.setNama(dataAdoption.getNamaHewan());
                rabbitViewHolder.setAlamat(dataAdoption.getLokasiHewan());
                rabbitViewHolder.setGambar(dataAdoption.getGambarHewan());

                rabbitViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Adoption2Activity.this, DetailAdoptionActivity.class);

                        intent.putExtra("namaHewan", dataAdoption.getNamaHewan());
                        intent.putExtra("alamatHewan", dataAdoption.getLokasiHewan());
                        intent.putExtra("gambarHewan", dataAdoption.getGambarHewan());
                        intent.putExtra("deskripsiHewan", dataAdoption.getDeskripsiHewan());
                        intent.putExtra("kategoriHewan", dataAdoption.getJenisHewan());
                        intent.putExtra("umurHewan", dataAdoption.getUmurHewan());
                        startActivity(intent);
                    }
                });
            }
        };

        rabbitRecycler.setAdapter(adapterRabbit);
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder{

        View myview;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setNama(String nama){
            TextView namaHewan = myview.findViewById(R.id.namaHewan);
            namaHewan.setText(nama);
        }

        public void setAlamat(String alamat){
            TextView alamatHewan = myview.findViewById(R.id.alamatHewan);
            alamatHewan.setText(alamat);
        }

        public void setGambar(String gambar){
            ImageView gambarHewan = myview.findViewById(R.id.gambarHewan);

            Picasso.get().load(gambar).networkPolicy(NetworkPolicy.OFFLINE).into(gambarHewan, new Callback() {
                @Override
                public void onSuccess() {

                }
                @Override
                public void onError(Exception e) {
                    Picasso.get().load(gambar).into(gambarHewan);
                }
            });
        }
    }

    public static class DogViewHolder extends RecyclerView.ViewHolder{

        View myview;
        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setNama(String nama){
            TextView namaHewan = myview.findViewById(R.id.namaHewan);
            namaHewan.setText(nama);
        }

        public void setAlamat(String alamat){
            TextView alamatHewan = myview.findViewById(R.id.alamatHewan);
            alamatHewan.setText(alamat);
        }

        public void setGambar(String gambar){
            ImageView gambarHewan = myview.findViewById(R.id.gambarHewan);

            Picasso.get().load(gambar).networkPolicy(NetworkPolicy.OFFLINE).into(gambarHewan, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(gambar).into(gambarHewan);
                }
            });
        }
    }

    public static class RabbitViewHolder extends RecyclerView.ViewHolder{

        View myview;
        public RabbitViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setNama(String nama){
            TextView namaHewan = myview.findViewById(R.id.namaHewan);
            namaHewan.setText(nama);
        }

        public void setAlamat(String alamat){
            TextView alamatHewan = myview.findViewById(R.id.alamatHewan);
            alamatHewan.setText(alamat);
        }

        public void setGambar(String gambar){
            ImageView gambarHewan = myview.findViewById(R.id.gambarHewan);

            Picasso.get().load(gambar).networkPolicy(NetworkPolicy.OFFLINE).into(gambarHewan, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(gambar).into(gambarHewan);
                }
            });
        }
    }
}