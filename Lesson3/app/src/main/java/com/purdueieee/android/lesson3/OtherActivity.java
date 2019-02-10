package com.purdueieee.android.lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    public void handleOnClick(View view) {
        Intent intent = new Intent(OtherActivity.this, MainActivity.class);
        EditText ptData = findViewById(R.id.ptData);
        intent.putExtra("key", ptData.getText().toString());
        startActivity(intent);
    }
}
