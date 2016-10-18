package me.ppting.retrofit.service;

import java.util.Map;
import me.ppting.retrofit.main.bean.UploadInfo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by PPTing on 2016/10/18.
 * 测试文件上传
 */

public interface Stay4ItService {
    @Multipart
    @POST("http://api.stay4it.com/v1/public/core/?service=user.updateAvatar")
    Call<UploadInfo> upload(@Part("access_token") RequestBody token, @Part MultipartBody.Part file);

    @Multipart //这个注解为http请求报文头添加 Content-Type: multipart/form-data; boundary=5b7b2ddf-bef2-4a32-ac21-e4662ea82771
    @POST("http://api.stay4it.com/v1/public/core/?service=user.updateAvatar")
    Call<UploadInfo> uploadMore(@PartMap Map<String, RequestBody> map);
}
