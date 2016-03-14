package com.jla.mvvmfilms.filmList;

import com.jla.mvvmfilms.BR;
import com.jla.mvvmfilms.R;
import com.jla.mvvmfilms.base.ViewModelBase;
import com.jla.mvvmfilms.filmDetail.FilmDetailNavigator;
import com.jla.mvvmfilms.model.Film;
import com.jla.mvvmfilms.service.FilmService;
import com.jla.mvvmfilms.service.PopularFilmsCallback;
import com.jla.mvvmfilms.util.CustomBindingAdapters.OnItemSelected;

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
    private final FilmDetailNavigator filmDetailNavigator;
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

    public FilmListViewModel(FilmService filmService, Context context, FilmDetailNavigator filmDetailNavigator) {
        this.filmService = filmService;
        this.context = context;
        this.filmDetailNavigator = filmDetailNavigator;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        filmService.getPopularFilms(listener);
    }

    public OnItemSelected onItemSelected = new OnItemSelected() {
        @Override
        public void onItemSelected(int position) {
            filmDetailNavigator.navigate(films.get(position).getFilm().getId());
        }
    };

    private void loadFilms(List<Film> filmsResponse) {
        final List<FilmViewModel> newFilms = new ArrayList<>();

        for (Film film : filmsResponse) {
            newFilms.add(new FilmViewModel(film));
        }

        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = () -> {
            films.clear();
            films.addAll(newFilms);
        };
        mainHandler.post(myRunnable);
    }
}
