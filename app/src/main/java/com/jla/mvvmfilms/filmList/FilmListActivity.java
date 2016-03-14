package com.jla.mvvmfilms.filmList;

import com.jla.mvvmfilms.MvvmFilmsApplication;
import com.jla.mvvmfilms.R;
import com.jla.mvvmfilms.base.ActivityBase;
import com.jla.mvvmfilms.base.ViewModelBase;
import com.jla.mvvmfilms.databinding.FilmListActivityBinding;
import com.jla.mvvmfilms.filmDetail.FilmDetailNavigator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

public class FilmListActivity extends ActivityBase {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FilmListActivityBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.film_list_activity);
        dataBinding.setListViewModel((FilmListViewModel) viewModel);
    }

    @Override
    protected ViewModelBase getViewModel() {
        return new FilmListViewModel(MvvmFilmsApplication.getInstance().getFilmService(), this, new FilmDetailNavigator(this));
    }
}
