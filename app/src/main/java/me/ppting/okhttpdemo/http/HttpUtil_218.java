package me.ppting.okhttpdemo.http;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import me.ppting.okhttpdemo.util.NetUtil;
import me.ppting.okhttpdemo.util.NetWorkUtil;
import me.ppting.okhttpdemo.util.OkHttpApplication;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PPTing on 2016/10/13.
 */

public class HttpUtil_218 {

    private Retrofit retrofit;
    private static volatile HttpUtil_218 instance = null;
    public HttpUtil_218() {



        retrofit = new Retrofit.Builder()
            .baseUrl(NetUtil.BASE_218)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static HttpUtil_218 getInstance(){
        HttpUtil_218 mInstance = instance;
        if (mInstance == null){
            synchronized (HttpUtil_218.class){
                mInstance = instance;
                if (mInstance == null){
                    mInstance = new HttpUtil_218();
                    instance = mInstance;
                }
            }
        }
        return mInstance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
