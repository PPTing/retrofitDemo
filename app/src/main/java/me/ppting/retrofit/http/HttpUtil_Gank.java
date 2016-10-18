package me.ppting.retrofit.http;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import me.ppting.retrofit.util.NetUtil;
import me.ppting.retrofit.util.NetWorkUtil;
import me.ppting.retrofit.util.RetrofitApplication;
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

public class HttpUtil_Gank {


    private Retrofit retrofit;
    private static volatile HttpUtil_Gank instance = null;
    public HttpUtil_Gank() {

        //http log 拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File responseCache = new File(RetrofitApplication.context.getCacheDir(),"responseCache");
        int cacheSize = 10 * 1024 * 1024;//10M
        Cache cache = new Cache(responseCache,cacheSize);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build();

        retrofit = new Retrofit.Builder()
            .baseUrl(NetUtil.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static HttpUtil_Gank getInstance(){
        HttpUtil_Gank mInstance = instance;
        if (mInstance == null){
            synchronized (HttpUtil_Gank.class){
                mInstance = instance;
                if (mInstance == null){
                    mInstance = new HttpUtil_Gank();
                    instance = mInstance;
                }
            }
        }
        return mInstance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    private Interceptor interceptor = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetwork()){
                //无网络
                request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            }
            Response response = chain.proceed(request);
            Response newResponse;
            if (NetWorkUtil.isNetwork()){
                newResponse = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control","max-age=0")
                    .build();
            }else {
                newResponse = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control","max-age="+ 60 * 60)//一个小时
                    .build();
            }
            return newResponse;
        }
    };
}
