package me.ppting.gank.http;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import me.ppting.gank.util.Gank;
import me.ppting.gank.util.NetUtil;
import me.ppting.gank.util.NetWorkUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by PPTing on 16/3/18.
 */
public class HttpUtils {

    private OkHttpClient mOkHttpClient;

    private Callback mCallback;


    private static volatile HttpUtils instance = null;
    private Context context = Gank.context;
    private static final String TAG = "HttpUtils";

    public static HttpUtils getInstance(){
        HttpUtils mInstance = instance;
        if (mInstance == null){
            synchronized (HttpUtils.class){
                mInstance = instance;
                if (mInstance == null){
                    mInstance = new HttpUtils();
                    instance = mInstance;
                }
            }
        }
        return mInstance;
    }

    public HttpUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY);

        File responseCache = new File(Gank.context.getCacheDir(),"responseCache");
        int cacheSize = 10 * 1024 * 1024;//10M
        Cache cache = new Cache(responseCache,cacheSize);
        mOkHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .cache(cache)
            .build();
    }

    private Interceptor interceptor = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            if (url.contains("login")){
                Log.d("HttpUtils "," 该 url 包含 login");
            }
            Log.d("HttpUtils","request url "+request.url().toString());
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
            //return response.newBuilder().removeHeader("Pragma")
            //    .header("Cache-Control", "max-age="+ 5)//一个小时
            //    .build();
        }
    };
    /**
     * post 方法
     * @param url 请求url
     * @param requestParams 参数
     * @param callback 回调
     */
    public void post(String url, RequestParams requestParams, Callback callback){

        this.mCallback = callback;
        Request request = new Request.Builder()
            .url(NetUtil.BASE_URL + url)
            .post(getPostBody(requestParams))
            .build();


        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override public void onFailure(Call call, IOException e) {
                mCallback.onFailed(call);
            }


            @Override public void onResponse(Call call, Response response) throws IOException {
                mCallback.onSuccess(response);
            }
        });

    }


    /**
     * 获取 post 方法参数
     * @param requestParams
     * @return
     */
    private RequestBody getPostBody(RequestParams requestParams){
        //RequestBody body ;
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String,Object> entry : requestParams.getMap().entrySet()) {
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }


    /**
     * get 方式
     * @param url
     * @param requestParams
     * @param callback
     */
    public void get(String url, RequestParams requestParams,Callback callback){
        this.mCallback = callback;
        Request request = new Request.Builder()
            .url(NetUtil.BASE_URL + getGetBody(url,requestParams))
            .build();
        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override public void onFailure(Call call, IOException e) {
                mCallback.onFailed(call);
            }



            @Override public void onResponse(Call call, Response response) throws IOException {
                mCallback.onSuccess(response);
            }
        });
    }


    /**
     *
     */
    private String getGetBody(String url, RequestParams requestParams){
        StringBuffer body = new StringBuffer();
        body.append(url);
        if (requestParams.getMap().size() > 0){
            body.append('?');
            for (Map.Entry<String,Object> entry : requestParams.getMap().entrySet()) {
                body.append(entry.getKey()).append('=').append(entry.getValue().toString()).append('&');
            }
            body.deleteCharAt(body.length() -1);
        }else {
            body.append("");
        }

        return body.toString();
    }

}
