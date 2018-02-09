package com.ljh.baselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ljh on 2016/11/29.
 */

public class NetworkUtils {
    private static ConnectivityManager mConnectivityManager = null;

    private static ConnectivityManager getConnectivityManager(Context context) {
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return mConnectivityManager;
    }


    /**
     * 判断是否具有网络连接
     *
     * @return
     */
    public static final boolean hasNetWorkConection(Context context) {
        // 获取连接活动管理器
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isAvailable());
    }


    /**
     * 当前网络是不是wifi
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            NetworkInfo mWiFiNetworkInfo = getConnectivityManager(context)
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}