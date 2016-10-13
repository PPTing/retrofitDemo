package me.ppting.okhttpdemo.login.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import me.ppting.okhttpdemo.BaseActivity;
import me.ppting.okhttpdemo.R;
import me.ppting.okhttpdemo.login.presenter.LoginContract;
import me.ppting.okhttpdemo.login.presenter.LoginPresenter;
import me.ppting.okhttpdemo.util.NetUtil;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginContract.View{

    private Button mPostButton;
    private Button mGetButton;
    private Button mHttpsButton;
    private EditText mUsername;
    private EditText mPassword;

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
        mHttpsButton = (Button) findViewById(R.id.https);
        mPostButton.setOnClickListener(this);
        mGetButton.setOnClickListener(this);
        mHttpsButton.setOnClickListener(this);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
    }


    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                loginPresenter.login(NetUtil.HttpMethod.GET,mUsername.getText().toString(),mPassword.getText().toString());
                break;
            case R.id.post:
                loginPresenter.login(NetUtil.HttpMethod.POST,mUsername.getText().toString(),mPassword.getText().toString());

                break;
            case R.id.https:
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
