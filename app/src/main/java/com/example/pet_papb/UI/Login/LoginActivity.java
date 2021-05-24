package com.example.pet_papb.UI.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText etEmail, etPassword;
    private Button signIn;
    private SwitchCompat switchAdmin;
    private FirebaseAuth auth;
    private String admins;
    private String users;
    private FirebaseDatabase getDatabase;
    private DatabaseReference db;
    private String GetUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register = (TextView) findViewById(R.id.textViewRegister);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.buttonLogin);
        signIn.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        etPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        switchAdmin = findViewById(R.id.switchAdminButton);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewRegister:
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.buttonLogin:
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(email.isEmpty()){
                    etEmail.setError("Email is required!");
                    etEmail.requestFocus();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.setError("Please enter a valid E-mail!");
                    etEmail.requestFocus();
                    return;
                }
                else if(password.isEmpty()){
                    etPassword.setError("Password is required!");
                    etPassword.requestFocus();
                    return;
                }
                else if(password.length() < 6){
                    etPassword.setError("Minimum password length should be 6 characters!");
                    etPassword.requestFocus();
                    return;
                }
                else if(switchAdmin.isChecked()){
                    adminLogin(email, password);
                }else{
                    userLogin(email, password);
                }
                break;
        }
    }

    private void userLogin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    db = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                    db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users user = snapshot.getValue(Users.class);
                            if(user.getStatus().equalsIgnoreCase("default")){
                                Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                auth.signOut();
                                Toast.makeText(LoginActivity.this, "Status mungkin admin", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login, please check your information!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void adminLogin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    db = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                    db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users user = snapshot.getValue(Users.class);
                            if(user.getStatus().equalsIgnoreCase("admin")){
                                Log.d(user.getStatus(), "onDataChange: ");
                                Intent intent = new Intent(LoginActivity.this, Homepage2Activity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                auth.signOut();
                                Toast.makeText(LoginActivity.this, "Status mungkin user biasa", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login, please check your information!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
