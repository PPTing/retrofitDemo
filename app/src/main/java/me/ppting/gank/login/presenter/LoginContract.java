package me.ppting.gank.login.presenter;

import me.ppting.gank.base.BasePresenter;
import me.ppting.gank.base.BaseView;
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
        void login(String name,String password);
        void post(String url, String desc, String who, String type, boolean debug);
        void getDayGank(String year, String month, String day);
        void getRepo(String tingya);
    }
}
