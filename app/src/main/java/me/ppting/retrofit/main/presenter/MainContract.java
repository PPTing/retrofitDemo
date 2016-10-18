package me.ppting.retrofit.main.presenter;

import android.app.Activity;
import android.content.Context;
import java.io.File;
import java.util.List;
import me.ppting.retrofit.base.BasePresenter;
import me.ppting.retrofit.base.BaseView;
import me.ppting.retrofit.main.view.MainActivity;

/**
 * Created by PPTing on 2016/9/26.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {

        void requestPermissionSuccess();
        void requestPermissionFail();
        void showRequestTips();
    }

    interface Presenter extends BasePresenter{
        @Override void onDestroy();
        void post(String url, String desc, String who, String type, boolean debug);
        void getDayGank(String year, String month, String day);
        void getRepo(String tingya);
        void upload(File file);
        void uploadMoreFile(List<File> fileList);
        void requestPermission(Activity activity);
        void requestPermissionResult(int requestCode, String[] permissions, int[] grantResults);
    }
}
