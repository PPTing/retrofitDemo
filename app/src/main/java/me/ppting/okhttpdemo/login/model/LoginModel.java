package me.ppting.okhttpdemo.login.model;

import me.ppting.okhttpdemo.util.BaseModel;
import org.json.JSONObject;

/**
 * Created by PPTing on 2016/9/29.
 */

public abstract class LoginModel extends BaseModel{

    public interface LoginModelCallback{
        void loginInfo(JSONObject data);
    }
    public abstract void login(int httpMethod, String username, String password);
}
