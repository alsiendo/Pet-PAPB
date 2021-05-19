package com.example.pet_papb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapterPetCare extends RecyclerView.Adapter<MyHolderPetCare>  {

    Context c;
    ArrayList<ModelPetCare> models; //Arraylist yang berparameter nilai dari class Model

    //Bikin constructor
    public MyAdapterPetCare(Context c, ArrayList<ModelPetCare> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolderPetCare onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_pet_care, null);//MElakukan inflate pada row


        return new MyHolderPetCare(view);//
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolderPetCare myHolder, int i) {
        myHolder.mtitle.setText(models.get(i).getTitle());// i merupakan posisi
        myHolder.mDesc.setText(models.get(i).getDescription());
        myHolder.mImageView.setImageResource(models.get(i).getImg());// disini menggunakan image resource karena kita akan menggunakan
        // folder resource yang dimana drawable

//        myHolder.setItemClickListener(new itemClickListener() {
//            @Override
//            public void onItemClickListener(View v, int position) {
//                String gTitle = models.get(position).getTitle();
//                String gDesc = models.get(position).getDescription();// object ini mengambil data dari activity sebelumnya
//                BitmapDrawable bitmapDrawable = (BitmapDrawable)myHolder.mImageView.getDrawable();// ambil gambar dari drawable
//
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();// gambar akan mendapatkan stream and bytes ? :v
//
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);// Compress image
////                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//                byte[] bytes = stream.toByteArray();
//
//                //Ambil data melalui intent
//                Intent intent = new Intent(c, PetCareDetailActivity.class);
////                intent.putExtra("iTitle", gTitle);
////                intent.putExtra("iDecs", gDesc);
////                intent.putExtra("iImage",bytes);
//                c.startActivity(intent);
//            }
//        });

        myHolder.setItemClickListener(new itemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                if(models.get(position).getTitle().equals("Panthera Vet")){
                    Intent intent = new Intent(c,PCMalangOneDetailActivity.class);
                    c.startActivity(intent);
                }else if (models.get(position).getTitle().equals("Alta Vet Clinic")){
                    Intent intent = new Intent(c, PCMalangTwoDetailActivity.class);
                    c.startActivity(intent);
                }

                if(models.get(position).getTitle().equals("Surabaya Animal Clinic")){
                    Intent intent = new Intent(c, PCSurabayaOneDetailActivity.class);
                    c.startActivity(intent);
                }else if (models.get(position).getTitle().equals("INI Veterinary Services")){
                    Intent intent = new Intent(c, PCSurabayaTwoDetailActivity.class);
                    c.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
