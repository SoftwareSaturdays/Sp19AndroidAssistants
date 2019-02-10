package com.purdueieee.android.lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        TextView tvLabel = findViewById(R.id.tvLabel);
        if (value != null) {
            tvLabel.setText(value);
        }
    }

    public void handleOnClick(View view) {
        //                    the activity we are coming from, the activity we want to go to
        Intent i = new Intent(MainActivity.this, OtherActivity.class);
        startActivity(i);
    }
}
