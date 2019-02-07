package com.purdueieee.android.lesson4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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
}
