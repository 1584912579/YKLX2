package com.example.asus.yklx2.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by asus on 2018/5/29.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
