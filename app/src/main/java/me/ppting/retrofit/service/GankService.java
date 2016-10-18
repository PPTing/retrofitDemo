package me.ppting.retrofit.service;

import java.util.Map;
import me.ppting.retrofit.main.bean.DayGankInfo;
import me.ppting.retrofit.main.bean.Post2GankInfo;
import me.ppting.retrofit.util.NetUtil;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by PPTing on 2016/10/18.
 * 测试 get 请求和 post 请求
 */

public interface GankService {

    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST("/api/add2gank")
    Call<Post2GankInfo> add2Gank(@FieldMap Map<String,Object> map);

    @GET(NetUtil.DailyGank+"{year}/{month}/{day}")
    Call<DayGankInfo> getDayGank(@Path("year") String year, @Path("month") String month, @Path("day") String day);

}
