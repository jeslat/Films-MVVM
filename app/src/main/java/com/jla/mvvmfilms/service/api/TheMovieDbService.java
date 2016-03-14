package com.jla.mvvmfilms.service.api;

import com.jla.mvvmfilms.service.api.model.ConfigurationResponse;
import com.jla.mvvmfilms.service.api.model.PopularFilmsResponse;

import retrofit.http.GET;
import retrofit.http.Query;

public interface TheMovieDbService {

    @GET("/movie/popular")
    PopularFilmsResponse getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/configuration")
    ConfigurationResponse getConfiguration(@Query("api_key") String apiKey);
}
