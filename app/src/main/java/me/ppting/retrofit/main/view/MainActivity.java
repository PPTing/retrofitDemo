package me.ppting.retrofit.main.view;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.ppting.retrofit.R;
import me.ppting.retrofit.base.BaseActivity;
import me.ppting.retrofit.main.model.DayGankInfo;
import me.ppting.retrofit.main.model.UploadInfo;
import me.ppting.retrofit.main.presenter.MainContract;
import me.ppting.retrofit.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements View.OnClickListener,MainContract.View{

    private Button mPostButton;
    private Button mGetButton;
    private Button mRepoButton;
    private Button mUploadButton;
    private Button mUploadMoreButton;

    private final static String TAG = MainActivity.class.getName();
    private MainPresenter mainPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }




    private void initView() {
        mainPresenter = new MainPresenter(this);
        mPostButton = (Button) findViewById(R.id.post);
        mGetButton = (Button) findViewById(R.id.get);
        mRepoButton = (Button) findViewById(R.id.repo);
        mUploadButton = (Button) findViewById(R.id.upload);
        mUploadMoreButton = (Button) findViewById(R.id.uploadMore);
        mPostButton.setOnClickListener(this);
        mGetButton.setOnClickListener(this);
        mRepoButton.setOnClickListener(this);
        mUploadButton.setOnClickListener(this);
        mUploadMoreButton.setOnClickListener(this);
    }


    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                mainPresenter.getDayGank("2016","09","10");
                break;
            case R.id.post:
                mainPresenter.post("https://google.com","描述","id","Android",true);
                break;
            case R.id.repo:
                mainPresenter.getRepo("tingya");
                break;
            case R.id.upload:
                File file = new File(Environment.getExternalStorageDirectory() + "/icon_equipment.png");
                mainPresenter.upload(this,file);
                break;
            case R.id.uploadMore:
                File firstFile1 = new File(Environment.getExternalStorageDirectory() + "/icon_equipment.png");
                File firstFile2 = new File(Environment.getExternalStorageDirectory() + "/icon_cupboard.png");
                List<File> fileList = new ArrayList<>();
                fileList.add(firstFile1);
                fileList.add(firstFile2);
                mainPresenter.uploadMoreFile(this,fileList);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mainPresenter.requestPermissionResult(requestCode,permissions,grantResults);
    }


    @Override public void setPresenter(MainContract.Presenter presenter) {

    }


    @Override public void requestPermissionSuccess() {
        Log.d(TAG,"申请被被允许");
    }


    @Override public void requestPermissionFail() {
        Log.d(TAG,"申请被拒绝");
    }


    @Override public void showRequestTips() {
        Toast.makeText(this,"需要读取sd卡中照片进行上传，请允许",Toast.LENGTH_LONG).show();

    }


    @Override public void listRepo(String repo) {
        Toast.makeText(this,repo,Toast.LENGTH_SHORT).show();
    }


    @Override public void getDailySuccess(DayGankInfo body) {
        Toast.makeText(this,"获取当天的gank成功",Toast.LENGTH_SHORT).show();
    }


    @Override public void getDailyFail() {
        Toast.makeText(this,"获取当天的gank失败",Toast.LENGTH_SHORT).show();
    }


    @Override public void uploadFileSuccess(UploadInfo uploadInfo) {
        Toast.makeText(this,"上传成功"+uploadInfo.getMsg(),Toast.LENGTH_SHORT).show();
    }


    @Override public void uploadFileFail() {
        Toast.makeText(this,"上传失败",Toast.LENGTH_SHORT).show();
    }


    @Override public void post2Gank(String string) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

}
