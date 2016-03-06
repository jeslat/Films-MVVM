package com.jla.mvvmfilms.service;

import com.jla.mvvmfilms.model.Film;

import java.util.List;

public interface PopularFilmsCallback {

    void setPopularFilms(List<Film> films);

    void onGeneralError();
}
