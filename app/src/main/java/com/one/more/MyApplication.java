package com.one.more;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import static com.one.more.BuildConfig.ISDEBUG;

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

        if (ISDEBUG){
            Logger
                    .init("more")               // default tag : PRETTYLOGGER or use just init()
                    .methodCount(3)            // default 2
                    .logLevel(LogLevel.FULL);  // default : LogLevel.FULL
        }else {
            Logger
                    .init("more")               // default tag : PRETTYLOGGER or use just init()
                    .methodCount(3)            // default 2
                    .logLevel(LogLevel.NONE);  // default : LogLevel.FULL
        }

    }


}
