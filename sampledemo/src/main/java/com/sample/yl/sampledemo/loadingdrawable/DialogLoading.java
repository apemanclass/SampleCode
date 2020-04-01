package com.sample.yl.sampledemo.loadingdrawable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.sample.yl.sampledemo.R;

/**
 * Created by jiazhui on 2018/1/24.
 * 自定义全局加载的dialog
 */
public class DialogLoading extends Dialog {
    private final long times = 1000;

    public DialogLoading(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 3 * 1000);

    }
}
