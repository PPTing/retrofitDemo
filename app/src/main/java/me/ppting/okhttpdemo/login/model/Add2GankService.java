package me.ppting.okhttpdemo.login.model;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by PPTing on 2016/10/13.
 */

public interface Add2GankService {
    @POST("/api/add2gank")
    Call<ResponseBody> add2Gank(@QueryMap Map<String,Object> map);
}
