package com.purdueieee.android.networkingday;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import java.util.List;

public class PokemonModel extends ViewModel {
    public class Pokemon {

    }

    private class PokemonFactory extends DataSource.Factory<Integer, Pokemon> {

        @Override
        public DataSource<Integer, Pokemon> create() {
            return new PokemonDataSource() {
                @NonNull
                @Override
                public <ToValue> DataSource<Integer, ToValue> mapByPage(@NonNull Function<List<Pokemon>, List<ToValue>> function) {
                    return super.mapByPage(function);
                }

                @NonNull
                @Override
                public <ToValue> DataSource<Integer, ToValue> map(@NonNull Function<Pokemon, ToValue> function) {
                    return super.map(function);
                }
            };
        }
    }

    LiveData<PagedList<Pokemon>> pokemonList;

    PokemonModel() {
        pokemonList = new LivePagedListBuilder<>(new PokemonFactory(), 20).build();
    }
}
