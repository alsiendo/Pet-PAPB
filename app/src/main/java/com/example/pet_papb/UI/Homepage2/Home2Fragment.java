package com.example.pet_papb.UI.Homepage2;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pet_papb.R;
import com.example.pet_papb.UI.PetCareActivity;
public class Home2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView petCare = (ImageView) view.findViewById(R.id.btnPetCare);

        petCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PetCareActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}