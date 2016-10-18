package me.ppting.retrofit.main.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.ppting.retrofit.http.HttpUtil_Gank;
import me.ppting.retrofit.http.HttpUtil_Github;
import me.ppting.retrofit.http.RequestParams;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PPTing on 2016/9/29.
 */

public class MainModelImpl extends MainModel {
    private static final String TAG = MainModelImpl.class.getName();
    private MainModel.MainModelCallback mainModelCallback;
    public MainModelImpl(MainModel.MainModelCallback callback){
        this.mainModelCallback = callback;
    }



    /**
     * 获取 某个user的repo
     * @param user
     */
    @Override public void getRepo(String user) {
        RepoService repoService = HttpUtil_Github.getInstance().create(RepoService.class);
        Call<ResponseBody> call = repoService.listRepo(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mainModelCallback.listRepo(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    public void onDestroy() {
        //infoHandler.removeCallbacksAndMessages(null);
    }


    /**
     * 提交gank
     * @param url
     * @param desc
     * @param who
     * @param type
     * @param debug
     */
    public void post(String url, String desc, String who, String type, boolean debug) {
        Add2GankService add2GankService = HttpUtil_Gank.getInstance().create(Add2GankService.class);
        RequestParams requestParams = new RequestParams();
        requestParams.addRequestParams("url",url);
        requestParams.addRequestParams("desc",desc);
        requestParams.addRequestParams("who",who);
        requestParams.addRequestParams("type",type);
        requestParams.addRequestParams("debug",debug);
        Call<ResponseBody> call = add2GankService.add2Gank(requestParams.getMap());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mainModelCallback.post2Gank(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    /**
     * 获取当天的gank内容
     * @param year
     * @param month
     * @param day
     */
    public void getDayGank(String year, String month, String day) {
        GetDayGankService getDayGankService = HttpUtil_Gank.getInstance().create(GetDayGankService.class);
        Call<DayGankInfo> call = getDayGankService.getDayGank(year,month,day);
        call.enqueue(new Callback<DayGankInfo>() {
            @Override
            public void onResponse(Call<DayGankInfo> call, Response<DayGankInfo> response) {
                mainModelCallback.daily(response.body());
            }


            @Override public void onFailure(Call<DayGankInfo> call, Throwable t) {

            }
        });
    }


    /**
     * 上传文件并携带一些文本信息
     * @param file
     */
    @Override public void uploadOneFile(File file) {


        //设置 Content-Type
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);

        //设置 requestFile 的 Content-Disposition form-data; name="pic"; filename="icon_equipment.png"
        MultipartBody.Part body = MultipartBody.Part.createFormData("pic", file.getName(), requestFile);

        UploadFileService uploadFileService = HttpUtil_Gank.getInstance().create(UploadFileService.class);

        Call<UploadInfo> call = uploadFileService.upload(
            RequestBody.create(MediaType.parse("multipart/form-data"), "token value jai485789hqn485yhhwb "),//携带的文字信息
            body);

        call.enqueue(new Callback<UploadInfo>() {
            @Override public void onResponse(Call<UploadInfo> call, Response<UploadInfo> response) {
                mainModelCallback.uploadFile(response.body());
            }


            @Override public void onFailure(Call<UploadInfo> call, Throwable t) {

            }
        });
    }


    /**
     * 传多个文件 应该 Map<String,ResponseBody>传，其中String是为了添加
     * Content-Disposition: form-data; name="pic"; filename="icon_equipment.png"
     * @param fileList
     */
    @Override public void uploadMoreFile(List<File> fileList) {
        Map<String, RequestBody> map = new HashMap<>();
        for (File file : fileList) {
            map.put(file.getName()+"\";filename=\""+file.getName(),RequestBody.create(MediaType.parse("image/png"),file));
        }

        UploadMoreFileService uploadMoreFileService = HttpUtil_Gank.getInstance().create(UploadMoreFileService.class);
        Call<UploadInfo> call = uploadMoreFileService.uploadMore(map);
        call.enqueue(new Callback<UploadInfo>() {
            @Override public void onResponse(Call<UploadInfo> call, Response<UploadInfo> response) {
                mainModelCallback.uploadFile(response.body());
            }


            @Override public void onFailure(Call<UploadInfo> call, Throwable t) {

            }
        });


    }
}
