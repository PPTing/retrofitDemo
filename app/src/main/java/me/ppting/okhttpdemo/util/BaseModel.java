package me.ppting.okhttpdemo.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import me.ppting.okhttpdemo.http.Callback;
import me.ppting.okhttpdemo.http.HttpUtils;
import me.ppting.okhttpdemo.http.RequestParams;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONObject;

/**
 * Created by PPTing on 2016/9/29.
 */

public abstract class BaseModel{
    public void sendHttpRequest(int httpMethod, String url, RequestParams requestParam){
        if (httpMethod == NetUtil.HttpMethod.POST) {
            HttpUtils.getInstance().post(url,requestParam,new MyHttpCallback(url));
        } else if (httpMethod == NetUtil.HttpMethod.GET) {
            HttpUtils.getInstance().get(url,requestParam,new MyHttpCallback(url));
        }
    }

    private class MyHttpCallback implements Callback {

        String url;

        private MyHttpCallback(String url){
            this.url = url;
        }
        @Override public void onSuccess(Response response) throws IOException {
            String responseData = response.body().string();
            sendMessage(url,responseData);
        }


        @Override public void onFailed(Call call) {

        }
    }

    /**
     * @param url
     * @param response
     */
    private void sendMessage(String url, String response){
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("jsonStr", response);
        message.setData(bundle);
        infoHandler.sendMessage(message);
    }

    public class InfoHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle dataBundle = msg.getData();
            try {
                JSONObject responseData = new JSONObject(dataBundle.getString("jsonStr"));
                handlerSuccessData(dataBundle.getString("url"), responseData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public InfoHandler infoHandler = new InfoHandler();

    public abstract void handlerSuccessData(String url, JSONObject data);
}
