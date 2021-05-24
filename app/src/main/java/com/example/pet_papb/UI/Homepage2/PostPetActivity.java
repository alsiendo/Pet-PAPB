package com.example.pet_papb.UI.Homepage2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pet_papb.R;
import com.squareup.picasso.Picasso;

public class PostPetActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    private EditText etNamaHewan, etUmurHewan, etDeskripsiHewan;
    private Spinner spLokasiHewan, spJenisHewan;
    private Button btnUpload, btnAttachImage, btnCancel;

    private ImageView ivImageHewan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post a Pet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spLokasiHewan = findViewById(R.id.spinLokasiHewan);
        spJenisHewan = findViewById(R.id.spinJenisHewan);
        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(this);
        btnAttachImage = findViewById(R.id.btnAttachImage);
        btnAttachImage.setOnClickListener(this);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        ivImageHewan = findViewById(R.id.ivImage);

        etNamaHewan = findViewById(R.id.etNamaHewan);
        etUmurHewan = findViewById(R.id.etUmurHewan);
        etDeskripsiHewan = findViewById(R.id.etDeskripsiHewan);

        // Set Spinner
        ArrayAdapter<CharSequence> adapterLokasi = ArrayAdapter.createFromResource(this, R.array.lokasiHewan, android.R.layout.simple_spinner_item);
        adapterLokasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLokasiHewan.setAdapter(adapterLokasi);
        spLokasiHewan.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterJenis = ArrayAdapter.createFromResource(this, R.array.jenisHewan, android.R.layout.simple_spinner_item);
        adapterJenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenisHewan.setAdapter(adapterJenis);
        spJenisHewan.setOnItemSelectedListener(this);
        // --Set Spinner



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAttachImage:
                Log.d("btnAttachImage", "onClick: ");
                openFileChooser();
                break;
            case R.id.btnCancel:
                break;
            case R.id.btnUpload:
                Log.d("btnUpload", "onClick: ");
                String namaHewan = etNamaHewan.getText().toString().trim();
                String umurHewan = etUmurHewan.getText().toString().trim();
                String jenisHewan = spJenisHewan.getSelectedItem().toString();
                String lokasiHewan = spLokasiHewan.getSelectedItem().toString();
                String deskripsihewan = etDeskripsiHewan.getText().toString().trim();

                if(namaHewan.isEmpty()){
                    etNamaHewan.setError("Nama is required!");
                    etNamaHewan.requestFocus();
                    return;
                }else if(umurHewan.isEmpty()){
                    etUmurHewan.setError("Umur is required!");
                    etUmurHewan.requestFocus();
                    return;
                }else if(deskripsihewan.isEmpty()){
                    etDeskripsiHewan.setError("Deskripsi is required!");
                    etDeskripsiHewan.requestFocus();
                    return;
                }else{
                    uploadPost(namaHewan, umurHewan, jenisHewan, lokasiHewan, deskripsihewan);
                }
                break;
        }
    }

    private void uploadPost(String nama, String umur, String jenis, String lokasi, String deskripsi){
        // Jangan lupa tambahkan gambar, Id pemilik, status adopsi nya juga belum
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();


        }
    }
}