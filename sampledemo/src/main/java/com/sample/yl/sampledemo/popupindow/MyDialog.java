package com.sample.yl.sampledemo.popupindow;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ${jz} on 2016/12/2.
 * 对dialog进行封装。
 */

public abstract class MyDialog extends Dialog {
    protected Context mContext;

    public MyDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initControls();
        regListener();
    }

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 初始化界面的控件
     */
    protected abstract void initControls();

    /**
     * 注册监听
     */
    protected abstract void regListener();

    /**
     * @param gravity 在屏幕中的位置。
     * @param w       dialog占屏幕的宽度。
     * @param h       dialog占屏幕的高度。
     */
    protected void setScreenSize(int gravity, float w, float h) {
        Window dialogWindow = getWindow();
        getWindow().setGravity(gravity);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * w);
        if (h != 0) {
            lp.height = (int) (d.heightPixels * h);
        }
        dialogWindow.setAttributes(lp);
    }
}
