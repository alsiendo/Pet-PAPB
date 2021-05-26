package com.example.pet_papb.UI.Homepage2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_papb.R;
import com.example.pet_papb.UI.Adoption2.PostPetActivity;
import com.example.pet_papb.UI.Homepage1.ChatFragment;
import com.example.pet_papb.UI.Homepage1.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Homepage2Activity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdoptionHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = new AdoptionHomeFragment();

                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new AdoptionHomeFragment();
                        break;
                    case R.id.nav_chat:
                        selectedFragment = new ChatFragment();
                        break;
                    case R.id.nav_myPet:
                        Intent intent = new Intent(Homepage2Activity.this, PostPetActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        };

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

        @SuppressLint("ResourceAsColor")
        public void setStatus(String status){
            TextView statusHewan = myview.findViewById(R.id.statusHewan);
            statusHewan.setText("Waiting new owner");
            if(status.equalsIgnoreCase("diambil")){
                statusHewan.setText("Got new owner");
                statusHewan.setBackgroundColor(R.color.warna_diambil);
            }
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

        @SuppressLint("ResourceAsColor")
        public void setStatus(String status){
            TextView statusHewan = myview.findViewById(R.id.statusHewan);
            statusHewan.setText("Waiting new owner");
            if(status.equalsIgnoreCase("diambil")){
                statusHewan.setText("Got new owner");
                statusHewan.setBackgroundColor(R.color.warna_diambil);
            }
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

        @SuppressLint("ResourceAsColor")
        public void setStatus(String status){
            TextView statusHewan = myview.findViewById(R.id.statusHewan);
            statusHewan.setText("Waiting new owner");
            if(status.equalsIgnoreCase("diambil")){
                statusHewan.setText("Got new owner");
                statusHewan.setBackgroundColor(R.color.warna_diambil);
            }
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