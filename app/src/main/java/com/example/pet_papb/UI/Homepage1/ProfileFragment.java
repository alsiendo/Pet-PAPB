package com.example.pet_papb.UI.Homepage1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pet_papb.Model.Users;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Login.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    View view;
    Button logOut;
    Button editProfile;
    Button changePass;

    CircleImageView profileImage;
    TextView username;
    TextView date;
    TextView gender;
    TextView address;

    DatabaseReference reference;
    FirebaseUser fuser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        logOut = (Button) view.findViewById(R.id.btnLogOut);
        profileImage = (CircleImageView) view.findViewById(R.id.profileImage);
        username = (TextView) view.findViewById(R.id.textUsername);
        date = (TextView) view.findViewById(R.id.textDate);
        gender = (TextView) view.findViewById(R.id.textGender);
        address = (TextView) view.findViewById(R.id.textAddress);

        changePass = view.findViewById(R.id.buttonChangePassword);

        editProfile = (Button) view.findViewById(R.id.buttonEdit);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                if(user.getImageURL().equals("default")){
                    profileImage.setImageResource(R.mipmap.ic_launcher);
                }else{
                    Glide.with(getContext()).load(user.getImageURL()).into(profileImage);
                }
                username.setText(user.getUserName());
                date.setText(user.getDate());
                gender.setText(user.getGender());
                address.setText(user.getAddress());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), StartActivity.class));
                getActivity().finish();
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}