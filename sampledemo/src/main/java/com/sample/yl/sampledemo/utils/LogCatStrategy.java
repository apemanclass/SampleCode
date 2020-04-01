package com.sample.yl.sampledemo.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import com.orhanobut.logger.LogStrategy;

/**
 * Created by ${jz} on 2019/12/16。
 * 自定义log策略类--->解决打印错乱
 */
public class LogCatStrategy implements LogStrategy {

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        Log.println(priority, randomKey() + tag, message);
    }

    private int last;

    private String randomKey() {
        int random = (int) (10 * Math.random());
        if (random == last) {
            random = (random + 1) % 10;
        }
        last = random;
        return String.valueOf(random);
    }
}
