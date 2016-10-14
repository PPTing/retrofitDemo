package me.ppting.gank.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by PPTing on 2016/10/11.
 */

public class NetWorkUtil {

    public static boolean isNetwork() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) Gank.context.
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetworkInfo!=null && mNetworkInfo.isConnected();
    }
}
