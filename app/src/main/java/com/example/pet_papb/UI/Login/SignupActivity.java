package com.example.pet_papb.UI.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet_papb.R;
import com.example.pet_papb.UI.Homepage1.HomepageActivity;
import com.example.pet_papb.UI.Homepage2.Homepage2Activity;
import com.example.pet_papb.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView login, registerUser;
    private EditText etUsername, etEmail, etPassword;
    private SwitchCompat switchAdmin;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        login = findViewById(R.id.textViewLogin);
        login.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.buttonRegister);
        registerUser.setOnClickListener(this);

        etUsername = (EditText) findViewById(R.id.editTextUsername);
        etEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        etPassword = (EditText) findViewById(R.id.editTextRegisterPassword);

        switchAdmin = findViewById(R.id.switchAdminButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonRegister:
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                // Callbacks ketika input tidak sesuai
                if (username.isEmpty()){
                    etUsername.setError("Username is required!");
                    etUsername.requestFocus();
                    return;
                }else if(email.isEmpty()){
                    etEmail.setError("E-mail is required!");
                    etEmail.requestFocus();
                    return;
                }else if(password.isEmpty()) {
                    etPassword.setError("Password is required!");
                    etPassword.requestFocus();
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.setError("Please provide valid E-mail!");
                    etEmail.requestFocus();
                    return;
                }else if(password.length() < 6){
                    etPassword.setError("Minimum password length should be 6 characters!");
                    etPassword.requestFocus();
                    return;
                }else{
                    register(username, email, password);
                }
                break;
        }
    }

    private void register(String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(switchAdmin.isChecked()){
                            if (task.isSuccessful()){
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                String userid = firebaseUser.getUid();

                                reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                Users user = new Users(userid, username, email, password, "blank", "blank", "blank", "blank", "Admin");

                                reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignupActivity.this, "Admin has been registered successfully!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(SignupActivity.this, Homepage2Activity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });

                            }else{
                                Toast.makeText(SignupActivity.this, "Failed to register the user. Email anda mungkin sudah digunakan.", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            if (task.isSuccessful()){
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                String userid = firebaseUser.getUid();

                                reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                Users user = new Users(userid, username, email, password, "blank", "blank", "blank", "blank", "Default");


                                reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignupActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(SignupActivity.this, HomepageActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(SignupActivity.this, "Failed to register the user. Email anda mungkin sudah digunakan.", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
    }
}