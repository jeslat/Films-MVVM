package com.jla.mvvmfilms.filmList;

import com.jla.mvvmfilms.base.ViewModelBase;
import com.jla.mvvmfilms.model.Film;

public class FilmViewModel extends ViewModelBase {

    private final Film film;

    public FilmViewModel(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }
}
