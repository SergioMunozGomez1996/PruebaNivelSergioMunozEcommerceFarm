package com.example.pruebanivelsergiomunoz;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private final List<Book> mValues;
    private View.OnClickListener onClickListener;

    public MyBookRecyclerViewAdapter(List<Book> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mContentView.setText(String.valueOf(mValues.get(position).getTitle()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if(onClickListener!=null)
            onClickListener.onClick(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Book mItem;

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