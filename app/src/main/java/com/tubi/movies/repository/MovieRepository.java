package com.tubi.movies.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tubi.movies.model.MovieItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for connecting to server and fetching data.
 */
public class MovieRepository {
    private static MovieRepository instance;

    private static final String TAG = "Repository";

    public static MovieRepository getInstance() {
        if (instance == null)
            instance = new MovieRepository();
        return instance;
    }

    /**
     * Method to query the server and retrieve data using retrofit.
     *
     * @return livedata containing list of movie item
     */
    public MutableLiveData<List<MovieItem>> fetchDataFromServer() {

        final MutableLiveData<List<MovieItem>> data = new MutableLiveData<>();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<MovieItem>> call = service.getMovieList();

        call.enqueue(new Callback<List<MovieItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieItem>> call, @NonNull Response<List<MovieItem>> response) {
                if (response != null && response.isSuccessful()) {
                    data.postValue(response.body());
                    if (response.body() != null)
                        Log.i("Successful response", response.body().toString());

                } else
                    Log.i(MovieRepository.TAG + "No response", "Response is null");
            }

            @Override
            public void onFailure(Call<List<MovieItem>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    /**
     * Method to query the server and retrieve data using retrofit.
     *
     * @return livedata containing details of movie item
     */
    public MutableLiveData<MovieItem> fetchMovieDetailsDataFromServer(String id) {

        final MutableLiveData<MovieItem> data = new MutableLiveData<>();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MovieItem> call = service.getMovieDetails(id);

        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                if (response != null && response.isSuccessful()) {
                    data.postValue(response.body());
                    if (response.body() != null)
                        Log.i("Successful response", response.body().toString());
                } else
                    Log.i("No response", "Response is null");
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

}
