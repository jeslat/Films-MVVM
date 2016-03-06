package com.jla.mvvmfilms.filmList;

import com.jla.mvvmfilms.BR;
import com.jla.mvvmfilms.R;
import com.jla.mvvmfilms.base.ViewModelBase;
import com.jla.mvvmfilms.model.Film;
import com.jla.mvvmfilms.service.FilmService;
import com.jla.mvvmfilms.service.PopularFilmsCallback;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

public class FilmListViewModel extends ViewModelBase {

    private final FilmService filmService;
    private final Context context;
    private final PopularFilmsCallback listener = new PopularFilmsCallback() {
        @Override
        public void setPopularFilms(final List<Film> filmsResponse) {
            loadFilms(filmsResponse);
        }

        @Override
        public void onGeneralError() {

        }
    };

    public final ObservableList<FilmViewModel> films = new ObservableArrayList<>();

    public final ItemView itemView = ItemView.of(BR.itemViewModel, R.layout.film_row);

    public FilmListViewModel(FilmService filmService, Context context) {
        this.filmService = filmService;
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        filmService.getPopularFilms(listener);
    }

    private void loadFilms(List<Film> filmsResponse) {
        final List<FilmViewModel> newFilms = new ArrayList<>();

        for (Film film : filmsResponse) {
            newFilms.add(new FilmViewModel(film));
        }

        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                films.clear();
                films.addAll(newFilms);
            }
        };
        mainHandler.post(myRunnable);
    }
}
