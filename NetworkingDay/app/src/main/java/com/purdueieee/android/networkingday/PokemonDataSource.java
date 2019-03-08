package com.purdueieee.android.networkingday;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;

import java.util.List;

abstract class PokemonDataSource extends DataSource<Integer, PokemonModel.Pokemon> {

    PokemonDataSource() {
    }

    @NonNull
    @Override
    public <ToValue> DataSource<Integer, ToValue> mapByPage(@NonNull Function<List<PokemonModel.Pokemon>, List<ToValue>> function) {
        return null;
    }

    @NonNull
    @Override
    public <ToValue> DataSource<Integer, ToValue> map(@NonNull Function<PokemonModel.Pokemon, ToValue> function) {
        return null;
    }

//    @Override
//    boolean isContiguous() {
//        return true;
//    }
}
