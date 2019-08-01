package com.tubi.movies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tubi.movies.R;
import com.tubi.movies.adapter.MyMovieRecyclerViewAdapter;
import com.tubi.movies.model.MovieItem;

public class MainActivity extends AppCompatActivity implements MyMovieRecyclerViewAdapter.OnMovieClickListener {

    protected static String SELECTED_ITEM = "selectedItem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MovieFragment.newInstance(3))
                    .commit();
        }
    }

    @Override
    public void onMovieClick(MovieItem movieItem) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        Bundle dataBundle = new Bundle();
        dataBundle.putParcelable(MainActivity.SELECTED_ITEM, movieItem);
        intent.putExtras(dataBundle);
        startActivity(intent);
    }
}
