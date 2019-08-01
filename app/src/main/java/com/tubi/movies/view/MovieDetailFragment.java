package com.tubi.movies.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.tubi.movies.R;
import com.tubi.movies.model.MovieItem;
import com.tubi.movies.util.Cache;
import com.tubi.movies.util.LRUCache;

public class MovieDetailFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    private ImageView movieImage;
    private TextView movieTitle;
    private MovieItem movieItem = new MovieItem();
    LRUCache<String, MovieItem> lruCache;

    // Using Singleton pattern and creating one instance for the fragment.
    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        View view = inflater.inflate(R.layout.fragment_movie_details, parent, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        movieImage = view.findViewById(R.id.image_view);
        movieTitle = view.findViewById(R.id.title);
        lruCache = Cache.getInstance().getLru();

        Bundle bundle = getActivity().getIntent().getExtras(); // Getting the Bundle object that pass from another activity

        if (bundle != null)
            movieItem = bundle.getParcelable(MainActivity.SELECTED_ITEM);

        if (movieItem != null) {
            String id = movieItem.getId();
            String title = movieItem.getTitle();
            String imageUrl = movieItem.getImage();
            if (lruCache.isValid(id, movieItem)) {
                movieTitle.setText(lruCache.get(id).getTitle());

                Glide.with(getActivity().getApplicationContext())
                        .load(lruCache.get(id).getImage())
                        .into(movieImage);
                movieTitle.setText(title);
            } else {
                lruCache.add(id, movieItem);
                movieTitle.setText(title);
                Glide.with(getActivity().getApplicationContext())
                        .load(imageUrl)
                        .into(movieImage);
                movieTitle.setText(title);
            }
        }
    }
}
