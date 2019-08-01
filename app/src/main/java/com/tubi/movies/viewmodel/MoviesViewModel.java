package com.tubi.movies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tubi.movies.model.MovieItem;
import com.tubi.movies.repository.MovieRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private final MovieRepository movieRepository = MovieRepository.getInstance();

    public LiveData<List<MovieItem>> getMovieList() {
        MutableLiveData<List<MovieItem>> movies;
        movies = movieRepository.fetchDataFromServer();
        return movies;
    }

    public LiveData<MovieItem> getMovieDetails(String id) {
        MutableLiveData<MovieItem> movies;
        movies = movieRepository.fetchMovieDetailsDataFromServer(id);
        return movies;
    }
}
