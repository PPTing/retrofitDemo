package me.ppting.gank.login.view;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.ppting.gank.R;
import me.ppting.gank.base.BaseActivity;
import me.ppting.gank.login.presenter.LoginContract;
import me.ppting.gank.login.presenter.LoginPresenter;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginContract.View{

    private Button mPostButton;
    private Button mGetButton;
    private Button mLoginButton;
    private EditText mUsername;
    private EditText mPassword;
    private Button mRepoButton;
    private Button mUploadButton;
    private Button mUploadMoreButton;

    private final static String TAG = LoginActivity.class.getName();
    private LoginPresenter loginPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }




    private void initView() {
        loginPresenter = new LoginPresenter(this);
        mPostButton = (Button) findViewById(R.id.post);
        mGetButton = (Button) findViewById(R.id.get);
        mLoginButton = (Button) findViewById(R.id.login);
        mRepoButton = (Button) findViewById(R.id.repo);
        mUploadButton = (Button) findViewById(R.id.upload);
        mUploadMoreButton = (Button) findViewById(R.id.uploadMore);
        mPostButton.setOnClickListener(this);
        mGetButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mRepoButton.setOnClickListener(this);
        mUploadButton.setOnClickListener(this);
        mUploadMoreButton.setOnClickListener(this);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
    }


    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                loginPresenter.getDayGank("2016","09","10");
                break;
            case R.id.post:
                loginPresenter.post("https://google.com","描述","id","Android",true);
                break;
            case R.id.login:
                loginPresenter.login(mUsername.getText().toString(),mPassword.getText().toString());
                break;
            case R.id.repo:
                loginPresenter.getRepo("tingya");
                break;
            case R.id.upload:
                File file = new File(Environment.getExternalStorageDirectory() + "/icon_equipment.png");
                loginPresenter.upload(file);
                break;
            case R.id.uploadMore:
                File firstFile1 = new File(Environment.getExternalStorageDirectory() + "/icon_equipment.png");
                File firstFile2 = new File(Environment.getExternalStorageDirectory() + "/icon_cupboard.png");
                List<File> fileList = new ArrayList<>();
                fileList.add(firstFile1);
                fileList.add(firstFile2);
                loginPresenter.uploadMoreFile(fileList);
                break;
            default:
                break;
        }
    }


    @Override public void loginSuccess(JSONObject data) {
        Log.d(TAG,data.toString());
    }


    @Override public void loginFailed(JSONObject data) {
        Log.d(TAG,data.toString());
    }



    @Override public void emptyUsername() {

    }


    @Override public void emptyPassword() {

    }


    @Override public void setPresenter(LoginContract.Presenter presenter) {

    }
}
