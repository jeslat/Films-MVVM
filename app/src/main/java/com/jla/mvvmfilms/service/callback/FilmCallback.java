package com.jla.mvvmfilms.service.callback;

import com.jla.mvvmfilms.model.Film;

public interface FilmCallback {

    void setFilm(Film film);

    void onGeneralError();
}
