package me.ppting.retrofit.main.model;

import java.util.Map;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by PPTing on 2016/10/17.
 */

public interface UploadMoreFileService {
    @Multipart //这个注解为http请求报文头添加 Content-Type: multipart/form-data; boundary=5b7b2ddf-bef2-4a32-ac21-e4662ea82771
    @POST("http://api.stay4it.com/v1/public/core/?service=user.updateAvatar")
    Call<ResponseBody> uploadMore(@PartMap Map<String, RequestBody> map);


}
