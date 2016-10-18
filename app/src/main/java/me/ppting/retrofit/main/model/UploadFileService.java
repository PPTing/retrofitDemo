package me.ppting.retrofit.main.model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by PPTing on 2016/10/16.
 */

public interface UploadFileService {
    @Multipart
    @POST("http://api.stay4it.com/v1/public/core/?service=user.updateAvatar")
    Call<UploadInfo> upload(@Part("access_token") RequestBody token, @Part MultipartBody.Part file);
}
