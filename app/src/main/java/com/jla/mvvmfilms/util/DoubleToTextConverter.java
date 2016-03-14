package com.jla.mvvmfilms.util;

import android.databinding.BindingConversion;

public class DoubleToTextConverter {

    @BindingConversion
    public static String doubleToTextConverter(double value) {
        return String.valueOf(value);
    }
}
