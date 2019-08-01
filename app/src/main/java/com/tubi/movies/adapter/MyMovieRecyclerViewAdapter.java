package com.tubi.movies.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tubi.movies.R;
import com.tubi.movies.model.MovieItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that displays a {@link MovieItem} from the provided list.
 */
public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyMovieRecyclerViewAdapter";

    private final List<MovieItem> mValues;
    private final OnMovieClickListener mListener;
    private final Context mContext;

    public MyMovieRecyclerViewAdapter(List<MovieItem> items, OnMovieClickListener listener, Context context) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());

        Glide.with(mContext)
                .load(mValues.get(position).getImage())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTitleView;
        public MovieItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.image_view);
            mTitleView = view.findViewById(R.id.title);
            mView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + mTitleView.getText();
        }

        @Override
        public void onClick(View view) {
            mListener.onMovieClick(mValues.get(getAdapterPosition()));
            Toast.makeText(mContext, "Selected movie position " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(MovieItem movieItem);
    }
}
