package com.jla.mvvmfilms.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivityBase extends AppCompatActivity {

    protected ViewModelBase viewModel;
    private ViewDataBinding dataBinding;

    protected abstract ViewModelBase getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        viewModel.onCreate();
    }

    @Override
    protected void onDestroy() {
        viewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onStop();
    }
}
