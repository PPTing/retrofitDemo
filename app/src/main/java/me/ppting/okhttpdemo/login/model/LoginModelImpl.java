package me.ppting.okhttpdemo.login.model;

import me.ppting.okhttpdemo.http.RequestParams;
import me.ppting.okhttpdemo.util.NetUtil;
import org.json.JSONObject;

/**
 * Created by PPTing on 2016/9/29.
 */

public class LoginModelImpl extends LoginModel {
    private LoginModel.LoginModelCallback loginModelCallback;
    public LoginModelImpl(LoginModel.LoginModelCallback callback){
        this.loginModelCallback = callback;

    }
    @Override public void login(int httpMethod, String username, String password) {
        RequestParams requestParam = new RequestParams();
        requestParam.addRequestParams("username",username);
        requestParam.addRequestParams("password",password);
        sendHttpRequest(httpMethod,NetUtil.LOGIN,requestParam);
    }


    @Override public void handlerSuccessData(String url, JSONObject data) {
        switch (url){
            case NetUtil.LOGIN:
                loginModelCallback.loginInfo(data);
                break;
            default:
                break;
        }
    }


    public void onDestroy() {
        infoHandler.removeCallbacksAndMessages(null);
    }
}
