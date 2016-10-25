package com.one.more;

import android.app.Application;

/**
 * Created by mcz on 16/10/12
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    public static Application getContext() {

        return myApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;

    }


}
