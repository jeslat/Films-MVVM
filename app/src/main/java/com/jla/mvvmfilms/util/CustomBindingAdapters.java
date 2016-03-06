package com.jla.mvvmfilms.util;

import com.squareup.picasso.Picasso;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class CustomBindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }
}
