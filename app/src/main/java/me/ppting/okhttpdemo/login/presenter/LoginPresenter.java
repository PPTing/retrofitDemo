package me.ppting.okhttpdemo.login.presenter;

import android.text.TextUtils;
import me.ppting.okhttpdemo.login.model.LoginModel;
import me.ppting.okhttpdemo.login.model.LoginModelImpl;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PPTing on 2016/9/26.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginModel.LoginModelCallback{

    private LoginContract.View mView;
    private LoginModelImpl loginModelImpl;
    public LoginPresenter(LoginContract.View view){
        this.mView = view;
        loginModelImpl = new LoginModelImpl(this);
    }


    @Override public void onDestroy() {
        loginModelImpl.onDestroy();
    }


    @Override public void login(int httpMethod, String name, String password) {
        if (TextUtils.isEmpty(name)){
            mView.emptyUsername();
        }else if (TextUtils.isEmpty(password)){
            mView.emptyPassword();
        }else {
            loginModelImpl.login(httpMethod,name,password);
        }
    }


    /**
     *
     * @param data
     */
    @Override public void loginInfo(JSONObject data) {
        try {
            if (data.getInt("error") == 0){
                mView.loginSuccess(data);
            }else {
                mView.loginFailed(data);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
