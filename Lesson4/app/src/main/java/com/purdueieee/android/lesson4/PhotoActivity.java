package com.purdueieee.android.lesson4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                Uri image_uri = (Uri) intent.getParcelableExtra(
                        Intent.EXTRA_STREAM);
                if (image_uri != null) {
                    displayImage(image_uri);
                }
            }
        }
    }

    private void displayImage(Uri image_uri) {
        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageURI(image_uri);
    }

    static final int REQUEST_PHOTO = 1;
    private Uri mPhotoUri;

    public void onClickTakePhoto(View view) throws IOException {
        Intent getPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile("tmp", ".jpg", storageDir);
        mPhotoUri = FileProvider.getUriForFile(this,
                "com.purdueieee.android.lesson4.fileprovider", image);
        getPhoto.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);

        if (getPhoto.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(getPhoto, REQUEST_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        displayImage(mPhotoUri);
//        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK && data != null) {
//            Bundle extras = data.getExtras();
//            Bitmap photo = (Bitmap) (extras != null ? extras.get("data") : null);
//
//            ImageView imageView = findViewById(R.id.image_view);
//            imageView.setImageBitmap(photo);
//        }
    }
}


















