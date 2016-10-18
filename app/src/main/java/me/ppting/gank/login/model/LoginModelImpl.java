package me.ppting.gank.login.model;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.ppting.gank.http.HttpUtil_Gank;
import me.ppting.gank.http.HttpUtil_218;
import me.ppting.gank.http.HttpUtil_Github;
import me.ppting.gank.http.RequestParams;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PPTing on 2016/9/29.
 */

public class LoginModelImpl extends LoginModel {
    private static final String TAG = LoginModelImpl.class.getName();
    private LoginModel.LoginModelCallback loginModelCallback;
    public LoginModelImpl(LoginModel.LoginModelCallback callback){
        this.loginModelCallback = callback;

    }


    /**
     * 登录到218
     * @param username
     * @param password
     */
    @Override public void login(String username, String password) {
        LoginService loginService = HttpUtil_218.getInstance().create(LoginService.class);
        RequestParams requestParams = new RequestParams();
        requestParams.addRequestParams("username",username);
        requestParams.addRequestParams("password",password);
        Call<ResponseBody> call = loginService.getLoginInfo(requestParams.getMap());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,"url "+call.request().url());
                    loginModelCallback.loginInfo(new JSONObject(response.body().string()));
                } catch (IOException e ) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    /**
     * 获取 某个user的repo
     * @param user
     */
    @Override public void getRepo(String user) {
        RepoService repoService = HttpUtil_Github.getInstance().create(RepoService.class);
        Call<ResponseBody> call = repoService.listRepo(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,""+call.request().url());
                    Log.d(TAG,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    @Override public void handlerSuccessData(String url, JSONObject data) {
        switch (url){

            default:
                break;
        }
    }


    public void onDestroy() {
        infoHandler.removeCallbacksAndMessages(null);
    }


    /**
     * 提交gank
     * @param url
     * @param desc
     * @param who
     * @param type
     * @param debug
     */
    public void post(String url, String desc, String who, String type, boolean debug) {
        Add2GankService add2GankService = HttpUtil_Gank.getInstance().create(Add2GankService.class);
        RequestParams requestParams = new RequestParams();
        requestParams.addRequestParams("url",url);
        requestParams.addRequestParams("desc",desc);
        requestParams.addRequestParams("who",who);
        requestParams.addRequestParams("type",type);
        requestParams.addRequestParams("debug",debug);
        Call<ResponseBody> call = add2GankService.add2Gank(requestParams.getMap());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,"url "+call.request().url());
                    Log.d(TAG,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    /**
     * 获取当天的gank内容
     * @param year
     * @param month
     * @param day
     */
    public void getDayGank(String year, String month, String day) {
        GetDayGankService getDayGankService = HttpUtil_Gank.getInstance().create(GetDayGankService.class);
        Call<DayGankInfo> call = getDayGankService.getDayGank(year,month,day);
        call.enqueue(new Callback<DayGankInfo>() {
            @Override
            public void onResponse(Call<DayGankInfo> call, Response<DayGankInfo> response) {
                Log.d(TAG,"get gank daily error "+response.body().isError());
            }


            @Override public void onFailure(Call<DayGankInfo> call, Throwable t) {

            }
        });
    }


    /**
     * 上传文件并携带一些文本信息
     * @param file
     */
    @Override public void uploadOneFile(File file) {


        //设置 Content-Type
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);

        //设置 requestFile 的 Content-Disposition form-data; name="pic"; filename="icon_equipment.png"
        MultipartBody.Part body = MultipartBody.Part.createFormData("pic", file.getName(), requestFile);

        UploadFileService uploadFileService = HttpUtil_Gank.getInstance().create(UploadFileService.class);

        Call<ResponseBody> call = uploadFileService.upload(
            RequestBody.create(MediaType.parse("multipart/form-data"), "token value jai485789hqn485yhhwb "),//携带的文字信息
            body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,""+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    /**
     * 传多个文件 应该 Map<String,ResponseBody>传，其中String是为了添加
     * Content-Disposition: form-data; name="pic"; filename="icon_equipment.png"
     * @param fileList
     */
    @Override public void uploadMoreFile(List<File> fileList) {
        Map<String, RequestBody> map = new HashMap<>();
        for (File file : fileList) {
            map.put(file.getName()+"\";filename=\""+file.getName(),RequestBody.create(MediaType.parse("image/png"),file));
        }

        UploadMoreFileService uploadMoreFileService = HttpUtil_Gank.getInstance().create(UploadMoreFileService.class);
        Call<ResponseBody> call = uploadMoreFileService.uploadMore(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
}
