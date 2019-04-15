package com.sample.yl.sampledemo.popupindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.sample.yl.sampledemo.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by ${jz} on 2018/12/17。
 * 悬浮式弹出界面
 */
public class SuspensionPopWind extends BasePopupWindow {

    public SuspensionPopWind(Context context) {
        super(context);
    }


    //入场动画和退场动画已经不再强制实现了，一般建议实现
    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(250 * 2, 0, 300);
    }

    //入场动画和退场动画已经不再强制实现了，一般建议实现
    @Override
    protected Animation onCreateDismissAnimation() {
        return null;
    }

    @Override
    public View onCreateContentView() {

        return null;
    }
}
