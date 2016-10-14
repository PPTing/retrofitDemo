package me.ppting.okhttpdemo.login.model;

import android.util.Log;
import java.io.IOException;
import me.ppting.okhttpdemo.http.HttpUtil_218;
import me.ppting.okhttpdemo.http.HttpUtil_Gank;
import me.ppting.okhttpdemo.http.HttpUtil_Github;
import me.ppting.okhttpdemo.http.RequestParams;
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
                    Log.d(TAG,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


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
}
