package com.purdueieee.android.lesson4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    InnerClass ic = new InnerClass();
    StaticInnerClass sic = new StaticInnerClass();

    class InnerClass {
        InnerClass() {
            // Reference to the instance of the outer class that
            // instantiated this one.
            MainActivity ma = MainActivity.this;
        }
    }

    static class StaticInnerClass {
        StaticInnerClass() {
            // Note that static inner classes do not have a reference
            // to an instance of the outer class.
            MainActivity ma = MainActivity.this;
        }
    }


    private static final int MY_LOCATION_REQUEST = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocationManager locationManager =
                (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocation(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }
    }

    private void updateLocation(Location location) {
        TextView textView = findViewById(R.id.tv_gps);
        textView.setText(location.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_LOCATION_REQUEST: {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Success!
                } else {
                    // Not granted, recover
                }
            }
        }
    }

    public void onSend(View view) {
        EditText editText = findViewById(R.id.editText);
        String address = editText.getText().toString();
        Uri uri = Uri.parse("geo:0,0?q=" + address);
        Intent map_intent = new Intent(Intent.ACTION_VIEW, uri);
        map_intent.setPackage("com.google.android.apps.maps");
        startActivity(map_intent);
    }

    public void onClickPhotoActivity(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }
}
