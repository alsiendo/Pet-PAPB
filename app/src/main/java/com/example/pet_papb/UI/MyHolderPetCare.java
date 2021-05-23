package com.example.pet_papb.UI;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pet_papb.R;

public class MyHolderPetCare extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mImageView;
    TextView mtitle, mDesc;
    itemClickListener itemClickListener;

    MyHolderPetCare(@NonNull View itemView) {
        super(itemView);
        this.mImageView = itemView.findViewById(R.id.petCare1);
        this.mtitle = itemView.findViewById(R.id.titlePetCare1);
        this.mDesc = itemView.findViewById(R.id.descriptionPetCare1);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());
    }

    public void setItemClickListener(itemClickListener ic){
        this.itemClickListener = ic;
    }
}
