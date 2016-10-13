package me.ppting.okhttpdemo.login.presenter;

import me.ppting.okhttpdemo.util.BasePresenter;
import me.ppting.okhttpdemo.util.BaseView;
import org.json.JSONObject;

/**
 * Created by PPTing on 2016/9/26.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void loginSuccess(JSONObject data);
        void emptyUsername();
        void emptyPassword();
        void loginFailed(JSONObject data);
    }

    interface Presenter extends BasePresenter{
        @Override void onDestroy();
        void login(int httpMethod, String name,String password);
    }
}
