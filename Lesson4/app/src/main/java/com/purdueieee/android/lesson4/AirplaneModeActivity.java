package com.purdueieee.android.lesson4;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AirplaneModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_mode);
    }

    AirplaneModeBroadcastReceiver broadcastReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new AirplaneModeBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }
}
