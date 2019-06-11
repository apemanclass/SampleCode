package com.sample.yl.mylibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.blankj.utilcode.util.ShellUtils;

import static com.blankj.utilcode.util.ShellUtils.execCmd;

/**
 * Created by ${jz} on 2018/8/8。
 */
public class NetUtils {
    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断网络是否可用
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    public static boolean isAvailableByPing() {
        ShellUtils.CommandResult result = execCmd("ping -c 1 -w 1 223.5.5.5", false);
        boolean ret = result.result == 0;
        if (result.errorMsg != null) {
//            isAvailableByPing errorMsg
            Log.e("isAvailableByPing", result.errorMsg);
        }
//        isAvailableByPing successMsg
        if (result.successMsg != null) {
            Log.d("isAvailableByPing", result.successMsg);
        }
        return ret;
    }

}
