package me.ppting.okhttpdemo.login.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by PPTing on 2016/10/13.
 */

public interface RepoService {
    //如果有前缀 / 就代表着是一个绝对路径。删除了那个前缀的 /， 你将会得到正确的、包含了 v3 路径的全 URL。
    @GET("/users/{user}/repos")
    Call<ResponseBody> listRepo(@Path("user") String user);
}
