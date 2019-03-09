package com.purdueieee.android.networkingday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity
implements PokemonFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PokemonFragment pokemonFragment = new PokemonFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, pokemonFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(Pokemon item) {
        // TODO Fill this out
        Log.i("MainActivity", "Clicked: " + item.toString());
    }
}
