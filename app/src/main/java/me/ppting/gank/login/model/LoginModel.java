package me.ppting.gank.login.model;

import java.io.File;
import me.ppting.gank.base.BaseModel;
import org.json.JSONObject;

/**
 * Created by PPTing on 2016/9/29.
 */

public abstract class LoginModel extends BaseModel{
    public abstract void uploadOneFile(File file);
    public abstract void uploadMoreFile(File file1, File file2);


    public interface LoginModelCallback{
        void loginInfo(JSONObject data);
    }
    public abstract void login(String username, String password);
    public abstract void getRepo(String user);

}
