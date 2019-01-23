package com.purdueieee.android.lesson3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final String[] mData;

    public MyAdapter(String[] data) {
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(mData[i]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View mItemLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemLayout = itemView;
        }

        public void bind(String datum) {
            TextView textView = mItemLayout.findViewById(R.id.word);
            textView.setText(datum);
        }
    }
}
