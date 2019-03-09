package com.purdueieee.android.networkingday;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.purdueieee.android.networkingday.PokemonFragment.OnListFragmentInteractionListener;
import com.purdueieee.android.networkingday.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyPokemonRecyclerViewAdapter
        extends PagedListAdapter<Pokemon, MyPokemonRecyclerViewAdapter.ViewHolder> {
    private static final DiffUtil.ItemCallback<Pokemon> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Pokemon>() {
                @Override
                public boolean areItemsTheSame(@NonNull Pokemon a, @NonNull Pokemon b) {
                    return a.mName.equals(b.mName);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Pokemon a, @NonNull Pokemon b) {
                    return a.equals(b);
                }
            };
    private final OnListFragmentInteractionListener mListener;
    private final PokemonModel mPokemonModel;

    MyPokemonRecyclerViewAdapter(PokemonModel model, OnListFragmentInteractionListener listener) {
        super(DIFF_CALLBACK);
        mPokemonModel = model;
        mListener = listener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.i("MPRVA", "onBindViewHolder(holder, " + position + ")");
        Pokemon pokemon = getItem(position);
        if (pokemon == null) {
            return;
        }
        holder.mItem = pokemon;
        holder.mIdView.setText(pokemon.mName);
        holder.mContentView.setText(pokemon.mUrl);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        PagedList<Pokemon> list = mPokemonModel.pokemonList.getValue();
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Pokemon mItem;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}
