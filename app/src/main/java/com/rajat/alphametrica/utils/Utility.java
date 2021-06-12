package com.rajat.alphametrica.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.TextView;

import com.rajat.alphametrica.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



}
