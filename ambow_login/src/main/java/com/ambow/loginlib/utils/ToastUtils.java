package com.ambow.loginlib.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {
    public static void showShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showTime(Context context, View view,int time, String msg) {
        Toast toast = null;
        if(toast==null){
            toast=new Toast(context);
        }
        toast.setDuration(time);
        toast.setView(view);
        toast.show();
    }

    public static void showView(Context ctx){

    }
}