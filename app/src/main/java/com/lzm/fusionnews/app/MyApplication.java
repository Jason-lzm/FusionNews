package com.lzm.fusionnews.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzm.fusionnews.BuildConfig;

import timber.log.Timber;

/**
 * Created by lzm on 2018/4/13.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

        Fresco.initialize(this);
    }
}
