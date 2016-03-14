package com.jla.mvvmfilms.filmDetail;

import android.content.Context;
import android.content.Intent;

public class FilmDetailNavigator {

    public static final String FILM_ID = "film_id";
    private final Context context;

    public FilmDetailNavigator(Context context) {
        this.context = context;
    }

    public void navigate(int filmId) {
        Intent intent = new Intent(context, FilmDetailActivity.class);
        intent.putExtra(FILM_ID, filmId);
        context.startActivity(intent);
    }
}
