package com.sample.yl.sampledemo;

import android.app.Activity;
import android.app.Application;
import android.view.Gravity;

import com.blankj.utilcode.util.Utils;
import com.hjq.toast.IToastStyle;
import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jz} on 2017/6/7.
 */

public class MyApplication extends Application {

    public static List<Activity> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);

        ToastUtils.init(this);
        //自定义Toast样式
        ToastUtils.initStyle(new IToastStyle() {
            @Override
            public int getGravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int getXOffset() {
                return 0;
            }

            @Override
            public int getYOffset() {
                return 0;
            }

            @Override
            public int getZ() {
                return 30;
            }

            @Override
            public int getCornerRadius() {
                return 6;
            }

            @Override
            public int getBackgroundColor() {
                return 0X88000000;
            }

            @Override
            public int getTextColor() {
                return 0XEEFFFFFF;
            }

            @Override
            public float getTextSize() {
                return 14;
            }

            @Override
            public int getMaxLines() {
                return 3;
            }

            @Override
            public int getPaddingLeft() {
                return 24;
            }

            @Override
            public int getPaddingTop() {
                return 16;
            }

            @Override
            public int getPaddingRight() {
                return getPaddingLeft();
            }

            @Override
            public int getPaddingBottom() {
                return getPaddingTop();
            }
        });

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 循环退出数组中的全部活动（Activity）
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            //判断当前活动是否结束，如果没有结束则结束当前activity。
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
