package com.tubi.movies.repository;

import com.tubi.movies.model.MovieItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GetDataService {

    @GET("movies")
    Call<List<MovieItem>> getMovieList();

    @GET("movies/{movieId}/repos")
    Call<MovieItem> getMovieDetails(@Path("movieId") String movieId);
}