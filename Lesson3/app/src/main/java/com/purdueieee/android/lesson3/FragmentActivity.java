package com.purdueieee.android.lesson3;

import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FragmentActivity extends AppCompatActivity
        implements DemoFragment.OnFragmentInteractionListener,
        ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        DemoFragment demoFragment = new DemoFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, demoFragment).commit();
    }

    public void onClickDemo(View view) {
        DemoFragment demoFragment = new DemoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, demoFragment).commit();
    }

    public void onClickList(View view) {
        Log.i("OnClickList", "Here");
        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, listFragment).commit();
    }

    @Override
    public void onFragmentInteraction(@ColorInt int color) {
        ConstraintLayout layout = findViewById(R.id.root_layout);
        layout.setBackgroundColor(color);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
