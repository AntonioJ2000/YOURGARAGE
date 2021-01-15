package com.anto.yourgarage.views;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext =   getApplicationContext();

        Realm.init(this);
    }

    public static Context getContext() {
        return sContext;
    }
}