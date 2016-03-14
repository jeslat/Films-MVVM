package com.jla.mvvmfilms.service.api;

import com.jla.mvvmfilms.service.api.model.ConfigurationResponse;
import com.jla.mvvmfilms.service.api.model.FilmResponse;
import com.jla.mvvmfilms.service.api.model.PopularFilmsResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TheMovieDbService {

    String API_KEY = "20ffea664862269a108e69164352dcd8";

    @GET("/movie/popular")
    PopularFilmsResponse getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/movie/{id}")
    FilmResponse getMovie(@Query("api_key") String apiKey, @Path("id") int id);

    @GET("/configuration")
    ConfigurationResponse getConfiguration(@Query("api_key") String apiKey);
}
