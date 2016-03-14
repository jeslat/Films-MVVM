package com.jla.mvvmfilms.service.callback;

import com.jla.mvvmfilms.model.Film;

import java.util.List;

public interface PopularFilmsCallback {

    void setPopularFilms(List<Film> films);

    void onGeneralError();
}
