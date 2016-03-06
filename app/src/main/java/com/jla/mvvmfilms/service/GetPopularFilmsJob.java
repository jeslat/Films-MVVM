package com.jla.mvvmfilms.service;

import com.jla.mvvmfilms.api.TheMovieDbAdapter;
import com.jla.mvvmfilms.api.TheMovieDbService;
import com.jla.mvvmfilms.api.mapper.FilmResponseDataMapper;
import com.jla.mvvmfilms.api.model.ConfigurationResponse;
import com.jla.mvvmfilms.api.model.PopularFilmsResponse;
import com.jla.mvvmfilms.model.Film;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.List;

public class GetPopularFilmsJob extends Job {

    private static final String API_KEY = "20ffea664862269a108e69164352dcd8";
    private static final String TAG = "GetPopularFilmsJob";
    private static final int PRIORITY = 1;
    private final PopularFilmsCallback callback;

    public GetPopularFilmsJob(PopularFilmsCallback callback) {
        super(new Params(PRIORITY).requireNetwork());
        this.callback = callback;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {
        try {
            TheMovieDbService theMovieDbService = new TheMovieDbAdapter().create();
            ConfigurationResponse configurationResponse = theMovieDbService.getConfiguration(API_KEY);
            PopularFilmsResponse popularFilmsResponse = theMovieDbService.getPopularMovies(API_KEY, 1);
            List<Film> popularFilms = FilmResponseDataMapper
                    .transform(popularFilmsResponse.getPopularFilms(), configurationResponse.getImages());
            callback.setPopularFilms(popularFilms);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onGeneralError();
        }
    }

    @Override
    protected void onCancel() {
    }
}
