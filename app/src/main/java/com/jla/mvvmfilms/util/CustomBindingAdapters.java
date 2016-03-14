package com.jla.mvvmfilms.util;

import com.squareup.picasso.Picasso;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.lang.reflect.Method;

public class CustomBindingAdapters {

//    @BindingAdapter("android:src")
//    public static void setImageUrl(ImageView view, String url) {
//        Picasso.with(view.getContext())
//                .load(url)
//                .into(view);
//    }

    @BindingAdapter("app:url")
    public static void setImageUrl(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }

    @BindingAdapter("app:onItemSelected")
    public static void OnItemSelected(RecyclerView recyclerView, final OnItemSelected listener) {
        final RecyclerView.OnItemTouchListener onItemTouch = new RecyclerItemClickListener(recyclerView.getContext(), (view, position) -> listener.onItemSelected(position));
        recyclerView.addOnItemTouchListener(onItemTouch);
    }

    public interface OnItemSelected {
        void onItemSelected(int position);
    }
}
