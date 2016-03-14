package com.jla.mvvmfilms.service.job;

import com.jla.mvvmfilms.model.Film;
import com.jla.mvvmfilms.service.api.TheMovieDbAdapter;
import com.jla.mvvmfilms.service.api.TheMovieDbService;
import com.jla.mvvmfilms.service.api.mapper.FilmResponseDataMapper;
import com.jla.mvvmfilms.service.api.model.ConfigurationResponse;
import com.jla.mvvmfilms.service.api.model.FilmResponse;
import com.jla.mvvmfilms.service.callback.FilmCallback;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

public class GetFilmJob extends Job {

    private static final int PRIORITY = 1;
    private final int id;
    private final FilmCallback callback;

    public GetFilmJob(int id, FilmCallback callback) {
        super(new Params(PRIORITY).requireNetwork());
        this.id = id;
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
            FilmResponse filmResponse = theMovieDbService.getMovie(TheMovieDbService.API_KEY, id);
            Film film = FilmResponseDataMapper.transform(filmResponse, configurationResponse.getImages());
            callback.setFilm(film);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onGeneralError();
        }
    }

    @Override
    protected void onCancel() {
    }
}
