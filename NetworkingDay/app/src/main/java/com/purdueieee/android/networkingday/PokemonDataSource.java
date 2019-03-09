package com.purdueieee.android.networkingday;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonDataSource extends PageKeyedDataSource<Integer, Pokemon> {

    private final int LIMIT = 20;
    private final String mUrl = "https://pokeapi.co/api/v2/pokemon/?limit=" + LIMIT + "&offset=";
    private RequestQueue mRequestQueue;
    private int mCount = -1;

    public PokemonDataSource(RequestQueue queue) {
        mRequestQueue = queue;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Pokemon> callback) {
        Log.i("PDS", "loadInitial(key=" + 0 + ")");
        JsonObjectRequest request = new JsonObjectRequest(
                StringRequest.Method.GET, mUrl + 0,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    List<Pokemon> pokemonList = getPokemon(response);
                    mCount = response.getInt("count");
                    callback.onResult(pokemonList, 0, mCount, null, LIMIT);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("PokemonResponseListener", " Initial could not unpack JSON.");
                }
            }
        }, null);
        mRequestQueue.add(request);
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer, Pokemon> callback) {
        Log.i("PDS", "loadBefore(key=" + params.key + ")");
        JsonObjectRequest request = new JsonObjectRequest(
                StringRequest.Method.GET, mUrl + params.key,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    List<Pokemon> pokemonList = getPokemon(response);
                    callback.onResult(pokemonList, params.key == 0 ? null : Math.max(0, params.key - LIMIT));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("PokemonResponseListener", "Before could not unpack JSON.");
                }
            }
        }, null);
        mRequestQueue.add(request);
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Pokemon> callback) {
        Log.i("PDS", "loadAfter(key=" + params.key + ")");
        JsonObjectRequest request = new JsonObjectRequest(
                StringRequest.Method.GET, mUrl + params.key,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    List<Pokemon> pokemonList = getPokemon(response);
                    callback.onResult(pokemonList, (params.key + LIMIT) > mCount ? null : params.key + LIMIT);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("PokemonResponseListener", "Before could not unpack JSON.");
                }
            }
        }, null);
        mRequestQueue.add(request);
    }

    private List<Pokemon> getPokemon(JSONObject response) throws JSONException {
        List<Pokemon> pokemonList = new ArrayList<>();
        JSONArray array = response.getJSONArray("results");
        for (int i = 0; i < array.length(); ++i) {
            JSONObject jsonPokemon = array.getJSONObject(i);
            String name = jsonPokemon.getString("name");
            String url = jsonPokemon.getString("url");
            Pokemon p = new Pokemon(name, url);
            pokemonList.add(p);
        }
        return pokemonList;
    }
}