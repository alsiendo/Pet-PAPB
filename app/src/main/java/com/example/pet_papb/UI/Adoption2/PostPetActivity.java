package com.example.pet_papb.UI.Adoption2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pet_papb.Model.DataAdoption;
import com.example.pet_papb.R;
import com.example.pet_papb.UI.Homepage2.Homepage2Activity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class PostPetActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText etNamaHewan, etUmurHewan, etDeskripsiHewan;
    private Spinner spLokasiHewan, spJenisHewan;
    private Button btnUpload, btnAttachImage, btnCancel;

    private ImageView ivImageHewan;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private ProgressDialog progressDialog ;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pet);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        mStorageRef = FirebaseStorage.getInstance().getReference("CatAdoptionList");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("CatAdoptionList");
        progressDialog = new ProgressDialog(PostPetActivity.this);

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
                openFileChooser();
                break;
            case R.id.btnCancel:
                Intent intent = new Intent(PostPetActivity.this, Homepage2Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnUpload:
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
                }else if(mImageUri == null){
                    btnAttachImage.setError("Harap sertakan gambar.");
                    btnAttachImage.requestFocus();
                }else{
                    if(jenisHewan.equalsIgnoreCase("Anjing")){
                        mStorageRef = FirebaseStorage.getInstance().getReference("DogAdoptionList");
                        mDatabaseRef = FirebaseDatabase.getInstance().getReference("DogAdoptionList");
                    }else if (jenisHewan.equalsIgnoreCase("Kelinci")){
                        mStorageRef = FirebaseStorage.getInstance().getReference("RabbitAdoptionList");
                        mDatabaseRef = FirebaseDatabase.getInstance().getReference("RabbitAdoptionList");
                    }
                    uploadPost(namaHewan, umurHewan, lokasiHewan, jenisHewan, deskripsihewan);
                }
                break;
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadPost(String nama, String umur, String lokasi, String jenis, String deskripsi){
        progressDialog.setTitle("Post is Uploading...");
        progressDialog.show();
        StorageReference storageReference2 = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
        storageReference2.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Post Uploaded Successfully ", Toast.LENGTH_LONG).show();
                storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String URLIMAGE = uri.toString();
                        DataAdoption dataAdoption = new DataAdoption(URLIMAGE, nama, umur, lokasi, jenis, deskripsi, firebaseUser.getUid(),"tersedia");

                        String ImageID = mDatabaseRef.push().getKey();
                        mDatabaseRef.child(ImageID).setValue(dataAdoption);

                        Intent intent = new Intent(PostPetActivity.this, Homepage2Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
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

            Picasso.get().load(mImageUri).into(ivImageHewan);
        }
    }
}