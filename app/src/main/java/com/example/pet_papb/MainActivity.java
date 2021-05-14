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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText etEmail, etPassword;
    private Button signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.textViewRegister);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.buttonLogin);
        signIn.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        etPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid E-mail!");
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

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Tempat redirect halaman pas login. DummyActivity ganti jadi punyamu.
                    startActivity(new Intent(MainActivity.this, HomepageActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Failed to login, please check your information!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}