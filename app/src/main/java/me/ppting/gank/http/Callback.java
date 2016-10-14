package me.ppting.gank.http;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by PPTing on 16/3/18.
 */
public interface Callback{
    void onSuccess(Response response) throws IOException;
    void onFailed(Call call);
}
