package com.example.pet_papb;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pet_papb.Model.DataAdoption;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class AdoptionActivity extends AppCompatActivity {

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
        LinearLayoutManager lmCat = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lmCat.setReverseLayout(true);
        lmCat.setStackFromEnd(true);
        catRecycler.setHasFixedSize(true);
        catRecycler.setLayoutManager(lmCat);

        // DOG RECYCLER VIEW
        dogRecycler = findViewById(R.id.dog_rv);
        LinearLayoutManager lmDog = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lmDog.setReverseLayout(true);
        lmDog.setStackFromEnd(true);
        dogRecycler.setHasFixedSize(true);
        dogRecycler.setLayoutManager(lmDog);

        // RABBIT RECYCLER VIEW
        rabbitRecycler = findViewById(R.id.rabbit_rv);
        LinearLayoutManager lmRabbit = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lmRabbit.setReverseLayout(true);
        lmRabbit.setStackFromEnd(true);
        rabbitRecycler.setHasFixedSize(true);
        rabbitRecycler.setLayoutManager(lmRabbit);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataAdoption, CatViewHolder> adapterCat = new FirebaseRecyclerAdapter<DataAdoption, CatViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption,
                        CatViewHolder.class,
                        catAdoptionDatabase
                ) {
            @Override
            protected void populateViewHolder(CatViewHolder catViewHolder, DataAdoption dataAdoption, int i) {
                catViewHolder.setNama(dataAdoption.getNamaHewan());
                catViewHolder.setAlamat(dataAdoption.getAlamathewan());
                catViewHolder.setGambar(dataAdoption.getGambarHewan());

                catViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdoptionActivity.this, DetailAdoptionActivity.class);

                        intent.putExtra("namaHewan", dataAdoption.getNamaHewan());
                        intent.putExtra("alamatHewan", dataAdoption.getAlamathewan());
                        intent.putExtra("gambarHewan", dataAdoption.getGambarHewan());
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
                        dogAdoptionDatabase
                ) {
            @Override
            protected void populateViewHolder(DogViewHolder dogViewHolder, DataAdoption dataAdoption, int i) {
                dogViewHolder.setNama(dataAdoption.getNamaHewan());
                dogViewHolder.setAlamat(dataAdoption.getAlamathewan());
                dogViewHolder.setGambar(dataAdoption.getGambarHewan());

                dogViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdoptionActivity.this, DetailAdoptionActivity.class);

                        intent.putExtra("namaHewan", dataAdoption.getNamaHewan());
                        intent.putExtra("alamatHewan", dataAdoption.getAlamathewan());
                        intent.putExtra("gambarHewan", dataAdoption.getGambarHewan());
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
                        rabbitAdoptionDatabase
                ) {
            @Override
            protected void populateViewHolder(RabbitViewHolder rabbitViewHolder, DataAdoption dataAdoption, int i) {
                rabbitViewHolder.setNama(dataAdoption.getNamaHewan());
                rabbitViewHolder.setAlamat(dataAdoption.getAlamathewan());
                rabbitViewHolder.setGambar(dataAdoption.getGambarHewan());

                rabbitViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdoptionActivity.this, DetailAdoptionActivity.class);

                        intent.putExtra("namaHewan", dataAdoption.getNamaHewan());
                        intent.putExtra("alamatHewan", dataAdoption.getAlamathewan());
                        intent.putExtra("gambarHewan", dataAdoption.getGambarHewan());
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