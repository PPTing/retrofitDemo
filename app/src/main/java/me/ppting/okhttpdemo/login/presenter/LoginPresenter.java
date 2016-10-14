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


    @Override public void login(String name, String password) {
        if (TextUtils.isEmpty(name)){
            mView.emptyUsername();
        }else if (TextUtils.isEmpty(password)){
            mView.emptyPassword();
        }else {
            loginModelImpl.login(name,password);
        }
    }





    /**
     * url	想要提交的网页地址
     desc	对干货内容的描述	单独的文字描述
     who	提交者 ID	干货提交者的网络 ID
     type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     debug	当前提交为测试数据
     */
    @Override public void post(String url,String desc, String who, String type, boolean debug) {
        loginModelImpl.post(url,desc,who,type,debug);
    }


    @Override public void getDayGank(String year, String month, String day) {
        if (TextUtils.isEmpty(year)){

        }else if (TextUtils.isEmpty(month)){

        }else if (TextUtils.isEmpty(day)){

        }else {
            loginModelImpl.getDayGank(year,month,day);

        }
    }


    @Override public void getRepo(String user) {
        if (TextUtils.isEmpty(user)){
            //...
        }else {
            loginModelImpl.getRepo(user);
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
