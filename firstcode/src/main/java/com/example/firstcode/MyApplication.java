package com.example.firstcode;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by jiazhui on 2017/12/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化工具类
        Utils.init(this);
    }
}
