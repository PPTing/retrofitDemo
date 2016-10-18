package me.ppting.retrofit.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by PPTing on 2016/10/11.
 */

public class NetWorkUtil {

    public static boolean isNetwork() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) RetrofitApplication.context.
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetworkInfo!=null && mNetworkInfo.isConnected();
    }
}
