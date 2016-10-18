package me.ppting.retrofit.main.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.List;
import me.ppting.retrofit.main.model.MainModel;
import me.ppting.retrofit.main.model.MainModelImpl;

/**
 * Created by PPTing on 2016/9/26.
 */

public class MainPresenter implements MainContract.Presenter, MainModel.MainModelCallback {

    private MainContract.View mView;
    private MainModelImpl loginModelImpl;
    private final static int REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 1;
    public MainPresenter(MainContract.View view){
        this.mView = view;
        loginModelImpl = new MainModelImpl(this);
    }


    @Override public void onDestroy() {
        loginModelImpl.onDestroy();
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


    @Override public void upload(File file) {
        if (file == null){
            //...
            Log.d("MainPresenter","file is null");
        }else {
            loginModelImpl.uploadOneFile(file);
        }
    }



    /**
     * 上传多个文件
     */
    @Override public void uploadMoreFile(List<File> fileList) {
        loginModelImpl.uploadMoreFile(fileList);
    }


    /**
     * 申请权限
     * @param activity
     */
    @Override public void requestPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23
            && PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            //用户已经拒绝过了
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                mView.showRequestTips();
            }
            ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE_ASK_STORAGE_PERMISSIONS);
            return;
        }else {
        }
    }


    @Override
    public void requestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_STORAGE_PERMISSIONS){
            Log.d("MainPresenter ","permissions "+permissions[0]);
            //申请 读取存储的权限
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mView.requestPermissionSuccess();
            }else if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                mView.requestPermissionFail();
            }
        }
    }
}
