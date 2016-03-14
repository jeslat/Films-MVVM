package com.jla.mvvmfilms.filmDetail;

import com.jla.mvvmfilms.base.ViewModelBase;
import com.jla.mvvmfilms.model.Film;
import com.jla.mvvmfilms.service.FilmService;
import com.jla.mvvmfilms.service.callback.FilmCallback;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;

public class FilmDetailViewModel extends ViewModelBase {

    private final FilmService filmService;
    private final Context context;
    private final FilmCallback listener = new FilmCallback() {
        @Override
        public void setFilm(Film film) {
            loadFilm(film);
        }

        @Override
        public void onGeneralError() {

        }
    };

    public ObservableField<Film> film;

    public FilmDetailViewModel(FilmService filmService, Context context) {
        this.filmService = filmService;
        this.context = context;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int filmId = bundle.getInt(FilmDetailNavigator.FILM_ID);
        filmService.getFilm(filmId, listener);
    }

    private void loadFilm(Film film) {
        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = () -> this.film = new ObservableField<>(film);
        mainHandler.post(myRunnable);
    }
}
