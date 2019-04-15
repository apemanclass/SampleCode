package com.sample.yl.sampledemo.popupindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.sample.yl.sampledemo.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by ${jz} on 2018/8/21。
 */
public class PopDialog extends BasePopupWindow {
    private View popupView;

    public PopDialog(Context context) {
        super(context);
        setClipChildren(false);
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
        popupView = createPopupById(R.layout.dialog_pop_info);
        return popupView;
    }
}
