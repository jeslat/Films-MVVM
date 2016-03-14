package com.jla.mvvmfilms.service.job;

import com.jla.mvvmfilms.service.callback.PopularFilmsCallback;
import com.jla.mvvmfilms.service.api.TheMovieDbAdapter;
import com.jla.mvvmfilms.service.api.TheMovieDbService;
import com.jla.mvvmfilms.service.api.mapper.FilmResponseDataMapper;
import com.jla.mvvmfilms.service.api.model.ConfigurationResponse;
import com.jla.mvvmfilms.service.api.model.PopularFilmsResponse;
import com.jla.mvvmfilms.model.Film;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.List;

public class GetPopularFilmsJob extends Job {

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
            ConfigurationResponse configurationResponse = theMovieDbService.getConfiguration(TheMovieDbService.API_KEY);
            PopularFilmsResponse popularFilmsResponse = theMovieDbService.getPopularMovies(TheMovieDbService.API_KEY, 1);
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
