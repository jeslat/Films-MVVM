package com.jla.mvvmfilms.filmDetail;

import com.jla.mvvmfilms.MvvmFilmsApplication;
import com.jla.mvvmfilms.R;
import com.jla.mvvmfilms.base.ActivityBase;
import com.jla.mvvmfilms.base.ViewModelBase;
import com.jla.mvvmfilms.databinding.FilmDetailActivityBinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

public class FilmDetailActivity extends ActivityBase {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FilmDetailActivityBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.film_detail_activity);
        dataBinding.setDetailViewModel((FilmDetailViewModel) viewModel);
    }

    @Override
    protected ViewModelBase getViewModel() {
        return new FilmDetailViewModel(MvvmFilmsApplication.getInstance().getFilmService(), this);
    }
}
