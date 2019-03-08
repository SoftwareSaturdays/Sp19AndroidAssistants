package com.purdueieee.android.networkingday;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.purdueieee.android.networkingday.dummy.DummyContent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
implements PokemonFragment.OnListFragmentInteractionListener {

//    private RequestQueue mRequestQueue;
//    private PokemonVolley mPokemonVolley;
//    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PokemonModel model = ViewModelProviders.of(this)
                .get(PokemonModel.class);

        PokemonFragment pokemonFragment = new PokemonFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, pokemonFragment).commit();

//        textView = findViewById(R.id.text_view);
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        mPokemonVolley = new PokemonVolley(mRequestQueue);
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest();

//        StringBuilder builder = new StringBuilder();
//        for (int i=0; i < 3; i++) {
//            Pokemon pokemon = mPokemonVolley.getPokemon(i);
//            builder.append(pokemon.mName).append('\n');
//        }
//        textView.setText(builder.toString());
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        // TODO Fill this out
        Log.i("MainActivity", "Clicked: " + item.toString());
    }
}
