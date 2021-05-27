package com.example.pet_papb.UI.Homepage1;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pet_papb.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText CurrentPassword, NewPassword, ConfirmPassword;
    Button BtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        BtnSave = findViewById(R.id.buttonSave);
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}