package com.tubi.movies.view;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.tubi.movies.R;

public class MovieDetailActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MovieDetailFragment.newInstance())
                    .commit();
        }
    }
}
