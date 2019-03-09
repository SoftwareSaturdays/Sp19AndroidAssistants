package com.purdueieee.android.networkingday;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class PokemonModel extends AndroidViewModel {
    final RequestQueue mQueue;
    LiveData<PagedList<Pokemon>> pokemonList;

    PokemonModel(Application application) {
        super(application);
        mQueue = Volley.newRequestQueue(application);
        pokemonList = new LivePagedListBuilder<>(
                new PokemonFactory(), 20).build();
    }

    private class PokemonFactory extends DataSource.Factory<Integer, Pokemon> {
        @Override
        public DataSource<Integer, Pokemon> create() {
            return new PokemonDataSource(mQueue);
        }
    }
}
