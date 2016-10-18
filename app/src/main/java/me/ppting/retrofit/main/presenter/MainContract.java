package me.ppting.retrofit.main.presenter;

import android.app.Activity;
import java.io.File;
import java.util.List;
import me.ppting.retrofit.base.BasePresenter;
import me.ppting.retrofit.base.BaseView;
import me.ppting.retrofit.main.bean.DayGankInfo;
import me.ppting.retrofit.main.bean.Post2GankInfo;
import me.ppting.retrofit.main.bean.UploadInfo;

/**
 * Created by PPTing on 2016/9/26.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {

        void requestPermissionSuccess();
        void requestPermissionFail();
        void showRequestTips();
        void listRepo(String repo);
        void getDailySuccess(DayGankInfo body);
        void getDailyFail();
        void uploadFileSuccess(UploadInfo uploadInfo);
        void uploadFileFail();
        void post2GankSuccess(Post2GankInfo info);
        void post2GankFail(Post2GankInfo info);
    }

    interface Presenter extends BasePresenter{
        @Override void onDestroy();
        void post(String url, String desc, String who, String type, boolean debug);
        void getDayGank(String year, String month, String day);
        void getRepo(String tingya);
        void upload(Activity activity, File file);
        void uploadMoreFile(Activity activity, List<File> fileList);
        void requestPermissionResult(int requestCode, String[] permissions, int[] grantResults);
    }
}
