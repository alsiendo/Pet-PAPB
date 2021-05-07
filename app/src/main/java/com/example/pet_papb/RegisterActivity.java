package com.example.pet_papb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView login, registerUser;

    private EditText etUsername, etEmail, etPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        login = (TextView) findViewById(R.id.textViewLogin);
        login.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.buttonRegister);
        registerUser.setOnClickListener(this);

        etUsername = (EditText) findViewById(R.id.editTextUsername);
        etEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        etPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewLogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.buttonRegister:
                buttonRegister();
                break;
        }
    }

    private void buttonRegister() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()){
            etUsername.setError("Username is required!");
            etUsername.requestFocus();
            return;
        }
        if(email.isEmpty()){
            etEmail.setError("E-mail is required!");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please provide valid E-mail!");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            etPassword.setError("Minimum password length should be 6 characters!");
            etPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(username, email, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to register the user, try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}