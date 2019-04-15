package com.example.nursesystem;

import android.app.Application;

/**
 * Created by ${jz} on 2018/6/12。
 */
public class MyApplication extends Application {
    public static SharePreferenceUtil preferenceUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        preferenceUtil = new SharePreferenceUtil(this);
    }
}
