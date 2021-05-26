package com.example.pet_papb.UI.Homepage1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet_papb.Model.Users;
import com.example.pet_papb.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                String usernameNew = username.getText().toString();
                HashMap hashMap = new HashMap();
                hashMap.put("userName", usernameNew);

                reference.child(fuser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(EditProfileActivity.this, "Your Data is Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}