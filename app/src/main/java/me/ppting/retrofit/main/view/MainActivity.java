package me.ppting.retrofit.main.view;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import me.ppting.retrofit.R;
import me.ppting.retrofit.base.BaseActivity;
import me.ppting.retrofit.http.HttpUtil_Gank;
import me.ppting.retrofit.http.RequestParams;
import me.ppting.retrofit.main.bean.DayGankInfo;
import me.ppting.retrofit.main.bean.Post2GankInfo;
import me.ppting.retrofit.main.bean.UploadInfo;
import me.ppting.retrofit.main.presenter.MainContract;
import me.ppting.retrofit.main.presenter.MainPresenter;
import me.ppting.retrofit.service.GankService;
import me.ppting.retrofit.util.NetUtil;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener,MainContract.View{

    private Button mPostButton;
    private Button mPostWithoutMvpButton;
    private Button mPostWithOkhttpButton;
    private Button mGetButton;
    private Button mRepoButton;
    private Button mUploadButton;
    private Button mUploadMoreButton;

    private final static String TAG = MainActivity.class.getName();
    private MainPresenter mainPresenter;
    private long startTime;
    private OkHttpClient okHttpClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }




    private void initView() {
        mainPresenter = new MainPresenter(this);
        mPostButton = (Button) findViewById(R.id.post);
        mPostWithoutMvpButton = (Button) findViewById(R.id.post_without_mvp);
        mPostWithOkhttpButton = (Button) findViewById(R.id.post_only_okhttp);
        mGetButton = (Button) findViewById(R.id.get);
        mRepoButton = (Button) findViewById(R.id.repo);
        mUploadButton = (Button) findViewById(R.id.upload);
        mUploadMoreButton = (Button) findViewById(R.id.uploadMore);
        mPostButton.setOnClickListener(this);
        mGetButton.setOnClickListener(this);
        mRepoButton.setOnClickListener(this);
        mUploadButton.setOnClickListener(this);
        mUploadMoreButton.setOnClickListener(this);
        mPostWithoutMvpButton.setOnClickListener(this);
        mPostWithOkhttpButton.setOnClickListener(this);
        okHttpClient = new OkHttpClient();
    }


    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                mainPresenter.getDayGank("2016","09","10");
                break;
            case R.id.post:
                startTime = System.currentTimeMillis();
                mainPresenter.post("https://google.com","描述","id","Android",true);
                break;
            case R.id.post_without_mvp:
                final long startTime = System.currentTimeMillis();
                GankService gankService = HttpUtil_Gank.getInstance().create(GankService.class);
                RequestParams requestParams = new RequestParams();
                requestParams.addRequestParams("url","https://google.com");
                requestParams.addRequestParams("desc","描述");
                requestParams.addRequestParams("who","id");
                requestParams.addRequestParams("type","Android");
                requestParams.addRequestParams("debug",true);
                Call<Post2GankInfo> call = gankService.add2Gank(requestParams.getMap());
                call.enqueue(new Callback<Post2GankInfo>() {
                    @Override
                    public void onResponse(Call<Post2GankInfo> call, Response<Post2GankInfo> response) {
                        long endTime = System.currentTimeMillis();
                        long tillTime = endTime - startTime;
                        Log.d(TAG,"耗时 "+tillTime);
                    }


                    @Override public void onFailure(Call<Post2GankInfo> call, Throwable t) {

                    }
                });
                break;
            case R.id.post_only_okhttp:
                final long starttime = System.currentTimeMillis();
                FormBody.Builder body = new FormBody.Builder();
                body.add("url","https://google.com");
                body.add("desc","描述");
                body.add("who","id");
                body.add("type","Android");
                body.add("debug","true");
                Request request = new Request.Builder()
                    .url(NetUtil.BASE_URL+"/api/add2gank")
                    .post(body.build())
                    .build();
                okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override public void onFailure(okhttp3.Call call, IOException e) {

                    }


                    @Override public void onResponse(okhttp3.Call call, okhttp3.Response response)
                        throws IOException {
                        long endTime = System.currentTimeMillis();
                        long tillTime = endTime - starttime;
                        Log.d(TAG,"耗时 with okhttp "+tillTime);
                    }
                });
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


    @Override public void post2GankSuccess(Post2GankInfo info) {
        Log.d(TAG,"耗时 mvp "+(System.currentTimeMillis() - startTime));
        Toast.makeText(this,info.getMsg(),Toast.LENGTH_SHORT).show();
    }


    @Override public void post2GankFail(Post2GankInfo info) {
        Toast.makeText(this,info.getMsg(),Toast.LENGTH_SHORT).show();
    }

}
