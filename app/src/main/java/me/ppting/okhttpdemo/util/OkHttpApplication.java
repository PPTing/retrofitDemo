package me.ppting.okhttpdemo.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by PPTing on 2016/10/11.
 */

public class OkHttpApplication extends Application{
    public static Context context;


    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
