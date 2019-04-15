package com.example.firstcode.chapter2;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazhui on 2018/1/3.
 * 活动的管理器类
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

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
