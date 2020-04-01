package com.sample.yl.sampledemo;

import android.app.Activity;
import android.app.Application;
import androidx.annotation.Nullable;
import android.view.Gravity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.hjq.toast.IToastStyle;
import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.sample.yl.sampledemo.retrofit.entity.ImgWeal;
import com.sample.yl.sampledemo.utils.LogCatStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jz} on 2017/6/7.
 */

public class MyApplication extends Application {

    public static List<ImgWeal> imgBean = null;

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

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .logStrategy(new LogCatStrategy())
                .methodCount(0)
                .methodOffset(7)
                .tag("Logger")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;//用于确定日志是否应该打印出来。;
            }
        });
        Logger.addLogAdapter(new DiskLogAdapter());

        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
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
