package com.purdueieee.android.myapplication;

import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FragmentActivity extends AppCompatActivity implements
        DemoFragment.OnFragmentInteractionListener,
        ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        DemoFragment demoFragment = new DemoFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, demoFragment).commit();
    }

    @Override
    public void onFragmentInteraction(int color) {
        ConstraintLayout layout = findViewById(R.id.root_layout);
        layout.setBackgroundColor(color);
    }

    public void onItemListClick(View view) {
        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, listFragment).commit();
    }

    public void onDemoClick(View view) {
        DemoFragment demoFragment = new DemoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, demoFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
