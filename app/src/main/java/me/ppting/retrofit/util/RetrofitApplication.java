package me.ppting.retrofit.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by PPTing on 2016/10/14.
 */

public class RetrofitApplication extends Application{
    public static Context context;


    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
