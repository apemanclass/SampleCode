package com.bsyx.schedule;

import android.app.Application;

/**
 * Created by ${jz} on 2019/9/5。
 */
public class MyApplication extends Application {
    public static SharePreferenceUtil preferenceUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        preferenceUtil = SharePreferenceUtil.getInstance(this);

    }
}
