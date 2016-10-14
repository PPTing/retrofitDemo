package me.ppting.gank.http;

import me.ppting.gank.util.NetUtil;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PPTing on 2016/10/13.
 */

public class HttpUtil_Github {
    private Retrofit retrofit;
    private static volatile HttpUtil_Github instance = null;
    public HttpUtil_Github() {
        retrofit = new Retrofit.Builder()
            .baseUrl(NetUtil.GITHUB)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static HttpUtil_Github getInstance(){
        HttpUtil_Github mInstance = instance;
        if (mInstance == null){
            synchronized (HttpUtil_Github.class){
                mInstance = instance;
                if (mInstance == null){
                    mInstance = new HttpUtil_Github();
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
