package com.purdueieee.android.networkingday;

import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PokemonVolley {
//    private final String TAG = "PokemonVolley";
//    private List<Pokemon> mPokemonList;
//    private RequestQueue mRequestQueue;
//    private String mUrlNext =
//            "https://pokeapi.co/api/v2/pokemon/";
//    private String mUrlPrevious = null;
//
//    public PokemonVolley(RequestQueue requestQueue) {
//        mRequestQueue = requestQueue;
//        mPokemonList = new ArrayList<>();
//    }
//
//    public Pokemon getPokemon(int id) {
//        while (id > mPokemonList.size()-1) {
//            Log.i(TAG, "getPokemon");
//            RequestFuture<JSONObject> future = RequestFuture.newFuture();
//            JsonObjectRequest pokemonRequest = new JsonObjectRequest(
//                    StringRequest.Method.GET, mUrlNext, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.i(TAG, "Received Reply");
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.i(TAG, "Error Reply");
//                        }
//                    }
//                    future, future
//                );
//
//            mRequestQueue.add(pokemonRequest);
//
//            try {
//                while(!future.isDone()) {Thread.sleep(1);}
//                JSONObject object = future.get();
//                Log.i(TAG, "Future Finished");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//
//            return new Pokemon(mRequestQueue, "Test", "Url");
//
//            Log.i(TAG, pokemonRequest.toString());
//            try {
//                jsonResponse[0] = future.get(
//                        3, TimeUnit.SECONDS);
//                Log.i(TAG, "Got it");
//                receivePokemon(jsonResponse[0]);
//                mUrlNext = jsonResponse[0].getString("next");
//                mUrlPrevious = jsonResponse[0].getString("previous");
//                if (id > jsonResponse[0].getInt("count")) {
//                    throw new RuntimeException("id is too large.");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Log.d(TAG, String.valueOf(jsonResponse[0] == null));
//        }
//        return mPokemonList.get(id);
//    }
//
//    private void receivePokemon(JSONObject response) throws JSONException {
//        JSONArray array = response.getJSONArray("results");
//        for (int i=0; i < array.length(); ++i) {
//            JSONObject json = array.getJSONObject(i);
//            Pokemon pokemon = new Pokemon(mRequestQueue,
//                    json.getString("name"),
//                    json.getString("url"));
//            mPokemonList.add(pokemon);
//        }
//    }
}
