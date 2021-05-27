package com.example.pet_papb.UI.Homepage1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet_papb.Model.Users;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Homepage2.Homepage2Activity;
import com.example.pet_papb.UI.Login.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText CurrentPassword, NewPassword, ConfirmPassword;
    FirebaseUser firebaseUser;
    DatabaseReference db;
    DatabaseReference reference;

    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        CurrentPassword = findViewById(R.id.editTextCurrentPassword);
        NewPassword = findViewById(R.id.editTextNewPassword);
        ConfirmPassword = findViewById(R.id.editTextConfirmPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSave:
                String PasswordLama = CurrentPassword.getText().toString();
                String PasswordBaru = NewPassword.getText().toString();
                String PasswordUlang = ConfirmPassword.getText().toString();
                fuser = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                Log.d(PasswordLama, "onClick Password Lama: ");
                Log.d(PasswordBaru, "onClick password Baru: ");
                Log.d(PasswordUlang, "onClick Ulang: ");

                if(PasswordLama.isEmpty()){
                    CurrentPassword.setError("Silahkan dilengkapi.");
                    CurrentPassword.requestFocus();
                }else if (PasswordBaru.isEmpty()){
                    NewPassword.setError("Silahkan dilengkapi.");
                    NewPassword.requestFocus();
                }else if(PasswordUlang.isEmpty()){
                    ConfirmPassword.setError("Silahkan dilengkapi");
                    ConfirmPassword.requestFocus();
                }else if(PasswordBaru != PasswordUlang){
                    ConfirmPassword.setError("Kata sandi tidak sama.");
                    ConfirmPassword.requestFocus();
                }else if (PasswordLama.equals(reference.child("password").get().toString())){
                    Log.d("Ini sama En", "onClick: ");
                }else{
                    db = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                    db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users user = snapshot.getValue(Users.class);
                            if(user.getPassword().equals(PasswordLama)){
                                fuser = FirebaseAuth.getInstance().getCurrentUser();
                                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                                reference.child("password").setValue(PasswordBaru);
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Users user = snapshot.getValue(Users.class);
                                        if(user.getStatus().equalsIgnoreCase("default")){
                                            Intent intent = new Intent(ChangePasswordActivity.this, HomepageActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            Intent intent = new Intent(ChangePasswordActivity.this, Homepage2Activity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }else{
                                Toast.makeText(ChangePasswordActivity.this, "Maaf, gagal mengubah kata sandi. Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                break;
        }
    }
}