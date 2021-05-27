package com.example.pet_papb.UI.Homepage1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet_papb.Model.Users;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Homepage2.Homepage2Activity;
import com.example.pet_papb.UI.Login.StartActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileActivity extends AppCompatActivity {


    CircleImageView profileImage;
    EditText username;
    EditText date;
    EditText gender;
    EditText address;
    Button save;
    DatabaseReference reference;

    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        profileImage = (CircleImageView) findViewById(R.id.profileImage);
        username = (EditText) findViewById(R.id.editTextUsername);
        date = (EditText) findViewById(R.id.editTextDate);
        gender = (EditText) findViewById(R.id.editTextGender);
        address = (EditText) findViewById(R.id.editTextAddress);
        save = (Button) findViewById(R.id.buttonSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuser = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                String nama = username.getText().toString();
                String tanggal = date.getText().toString();
                String jenisKelamin = gender.getText().toString();
                String alamat = address.getText().toString();

                reference.child("userName").setValue(nama);
                reference.child("date").setValue(tanggal);
                reference.child("gender").setValue(jenisKelamin);
                reference.child("address").setValue(alamat);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users user = snapshot.getValue(Users.class);
                        if(user.getStatus().equalsIgnoreCase("default")){
                            Intent intent = new Intent(EditProfileActivity.this, HomepageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent(EditProfileActivity.this, Homepage2Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}