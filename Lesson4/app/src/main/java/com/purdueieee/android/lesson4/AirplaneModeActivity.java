package com.purdueieee.android.lesson4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AirplaneModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_mode);
    }

    AirplaneModeBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AirplaneModeActivity", "Resume register receiver");
        mBroadcastReceiver = new AirplaneModeBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AirplaneModeActivity", "Pause unregister receiver");
        unregisterReceiver(mBroadcastReceiver);
    }

    private class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("AirplaneModeBroadcastReceiver", "onReceive");
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            String status;
            if (isAirplaneModeOn) {
                status = "Enabled";
            } else {
                status = "Disabled";
            }

            TextView textView = AirplaneModeActivity.this.findViewById(R.id.airplane_status);
            textView.setText(status);
        }
    }
}
