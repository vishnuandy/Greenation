package com.protagonist.greennation;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by croyon11 on 10/24/2016.
 */
public class MyApplication extends Application {


    private static MyApplication sInstance;


    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;



    }

}

