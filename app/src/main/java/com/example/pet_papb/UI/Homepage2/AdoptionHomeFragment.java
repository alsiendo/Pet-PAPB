package com.example.pet_papb.UI.Homepage2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pet_papb.Model.DataAdoption;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Adoption1.DetailAdoptionActivity;
import com.example.pet_papb.UI.Homepage2.Adoption2Activity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class AdoptionHomeFragment extends Fragment {
    private RecyclerView catRecycler;
    private RecyclerView dogRecycler;
    private RecyclerView rabbitRecycler;

    private DatabaseReference catAdoptionDatabase;
    private DatabaseReference dogAdoptionDatabase;
    private DatabaseReference rabbitAdoptionDatabase;

    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_adoption_home, container, false);

        // CAT RECYCLER VIEW
        catRecycler = view.findViewById(R.id.cat_rv);
        LinearLayoutManager lmCat = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        lmCat.setReverseLayout(true);
        lmCat.setStackFromEnd(true);
        catRecycler.setHasFixedSize(true);
        catRecycler.setLayoutManager(lmCat);

        // DOG RECYCLER VIEW
        dogRecycler = view.findViewById(R.id.dog_rv);
        LinearLayoutManager lmDog = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        lmDog.setReverseLayout(true);
        lmDog.setStackFromEnd(true);
        dogRecycler.setHasFixedSize(true);
        dogRecycler.setLayoutManager(lmDog);

        // RABBIT RECYCLER VIEW
        rabbitRecycler = view.findViewById(R.id.rabbit_rv);
        LinearLayoutManager lmRabbit = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        lmRabbit.setReverseLayout(true);
        lmRabbit.setStackFromEnd(true);
        rabbitRecycler.setHasFixedSize(true);
        rabbitRecycler.setLayoutManager(lmRabbit);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        catAdoptionDatabase = FirebaseDatabase.getInstance().getReference().child("CatAdoptionList");
        dogAdoptionDatabase = FirebaseDatabase.getInstance().getReference().child("DogAdoptionList");
        rabbitAdoptionDatabase = FirebaseDatabase.getInstance().getReference().child("RabbitAdoptionList");




        Query queryCat = catAdoptionDatabase.orderByChild("idPemilik").equalTo(firebaseUser.getUid());
        Query queryDog = dogAdoptionDatabase.orderByChild("idPemilik").equalTo(firebaseUser.getUid());
        Query queryRabbit = rabbitAdoptionDatabase.orderByChild("idPemilik").equalTo(firebaseUser.getUid());

        FirebaseRecyclerAdapter<DataAdoption, Adoption2Activity.CatViewHolder> adapterCat = new FirebaseRecyclerAdapter<DataAdoption, Adoption2Activity.CatViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption_admin,
                        Adoption2Activity.CatViewHolder.class,
                        queryCat
                ) {
            @Override
            protected void populateViewHolder(Adoption2Activity.CatViewHolder catViewHolder, DataAdoption dataAdoption, int i) {
                catViewHolder.setNama(dataAdoption.getNamaHewan());
                catViewHolder.setGambar(dataAdoption.getGambarHewan());
                catViewHolder.setStatus(dataAdoption.getStatusKepemilikan());

                catViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DetailAdoptionActivity.class);
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

        FirebaseRecyclerAdapter<DataAdoption, Adoption2Activity.DogViewHolder> adapterDog = new FirebaseRecyclerAdapter<DataAdoption, Adoption2Activity.DogViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption_admin,
                        Adoption2Activity.DogViewHolder.class,
                        queryDog
                ) {
            @Override
            protected void populateViewHolder(Adoption2Activity.DogViewHolder dogViewHolder, DataAdoption dataAdoption, int i) {
                dogViewHolder.setNama(dataAdoption.getNamaHewan());
                dogViewHolder.setGambar(dataAdoption.getGambarHewan());
                dogViewHolder.setStatus(dataAdoption.getStatusKepemilikan());

                dogViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DetailAdoptionActivity.class);

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

        FirebaseRecyclerAdapter<DataAdoption, Adoption2Activity.RabbitViewHolder> adapterRabbit = new FirebaseRecyclerAdapter<DataAdoption, Adoption2Activity.RabbitViewHolder>
                (
                        DataAdoption.class,
                        R.layout.item_data_adoption_admin,
                        Adoption2Activity.RabbitViewHolder.class,
                        queryRabbit
                ) {
            @Override
            protected void populateViewHolder(Adoption2Activity.RabbitViewHolder rabbitViewHolder, DataAdoption dataAdoption, int i) {
                rabbitViewHolder.setNama(dataAdoption.getNamaHewan());
                rabbitViewHolder.setGambar(dataAdoption.getGambarHewan());
                rabbitViewHolder.setStatus(dataAdoption.getStatusKepemilikan());

                rabbitViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DetailAdoptionActivity.class);

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

        Log.d("HALOO", "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
    }

}