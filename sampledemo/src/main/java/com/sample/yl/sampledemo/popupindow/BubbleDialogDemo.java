package com.sample.yl.sampledemo.popupindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


import com.sample.yl.mylibrary.happybubble.BubbleDialog;
import com.sample.yl.sampledemo.R;


/**
 * Created by ${jz} on 2018/12/5。
 */
public class BubbleDialogDemo extends BubbleDialog {

    public BubbleDialogDemo(Context context) {
        super(context);
        calBar(false);
        setPosition(Position.RIGHT);
        setLayout(300, 300, 0);
        setTransParentBackground();
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_mission_txt, null);
        addContentView(rootView);

    }
}
