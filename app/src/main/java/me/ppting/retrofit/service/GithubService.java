package me.ppting.retrofit.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by PPTing on 2016/10/18.
 * 测试 get 请求
 */

public interface GithubService {
    //如果有前缀 / 就代表着是一个绝对路径。
    @GET("/users/{user}/repos")
    Call<ResponseBody> listRepo(@Path("user") String user);
}
