package com.sikop.sikop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadImage extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button btnchooseimage;
    private Button btnupload;
    private TextView tx_filename;
    private EditText etx_filename;
    private ImageView imageView;
    private ProgressBar progressBar;

    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private StorageTask storageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        btnchooseimage = (Button) findViewById(R.id.btn_choseimg);
        btnchooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        btnupload = (Button) findViewById(R.id.btn_upload);
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storageTask !=null && storageTask.isInProgress()){
                    Toast.makeText(UploadImage.this, "Upload in Progress", Toast.LENGTH_SHORT).show();

                }else {
                    uploadFile();
                }


            }
        });
        tx_filename = (TextView) findViewById(R.id.text_upload);
        tx_filename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageActivity();

            }
        });
        imageView = (ImageView) findViewById(R.id.imagv);
        etx_filename = (EditText) findViewById(R.id.text_filename);
        progressBar = (ProgressBar) findViewById(R.id.progresbar);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");


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

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null  ){
            imageUri = data.getData();

            Picasso.get().load(imageUri).into(imageView);
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    };

    private void uploadFile(){

        if (imageUri !=null){

            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageUri));

            storageTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(UploadImage.this, "Upload Succesful", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(etx_filename.getText().toString().trim(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString());
                            String uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadImage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        }else{
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openImageActivity(){
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }


}