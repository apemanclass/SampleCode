package com.sample.yl.sampledemo.popupindow;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sample.yl.sampledemo.R;

/**
 * Created by ${jz} on 2018/6/8。
 * 封装自定义通用的消息提示框
 */
public class BaseDialog extends Dialog {
    private TextView tvTitle, tvMessage;

    //从外界设置title文本和消息文本
    private String titleStr, messageStr;
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

    private Button btnCancel, btnConfirm;

    private int visible = View.VISIBLE;

    private Boolean isCancelable = true;

    private OnCancelOnclickListener cancelOnclickListener;//取消按钮被点击了的监听器
    private OnConfirmOnclickListener confirmOnclickListener;//确定按钮被点击了的监听器

    public BaseDialog(Context context) {
        super(context, R.style.Dialog_FS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_whole_dialog);

        initControls();
        initData();
        initEvent();
        //设置点击屏幕空白部分dialog是否消失
        setCanceledOnTouchOutside(isCancelable);
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            tvTitle.setText(titleStr);
        }
        if (messageStr != null) {
            tvMessage.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            btnConfirm.setText(yesStr);
        }
        if (noStr != null) {
            btnCancel.setText(noStr);
        }
        btnConfirm.setVisibility(visible);
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置取消按钮被点击后，向外界提供监听
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
        //设置确定按钮被点击后，向外界提供监听
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmOnclickListener != null) {
                    confirmOnclickListener.onConfirmClick();
                }
            }
        });
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setMessage(String message) {
        messageStr = message;
    }

    public void setCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
    }

    /**
     * 设置确定按钮隐藏
     */
    public void setPositiveVisible(int isShow) {
        visible = isShow;
    }

    public void setNegativeButton(String text, OnCancelOnclickListener listener) {
        if (text != null) {
            noStr = text;
        }
        this.cancelOnclickListener = listener;
    }

    public void setPositiveButton(String text, OnConfirmOnclickListener listener) {
        if (text != null) {
            yesStr = text;
        }
        this.confirmOnclickListener = listener;
    }

    /**
     * 设置发起托管请求对dialog的状态的处理
     */
    public void setConfirmText(String str) {
        btnConfirm.setEnabled(false);
        btnConfirm.setText(str);
        //btnConfirm.setBackgroundResource(R.drawable.dialog_bg_ok_disable);
        btnCancel.setEnabled(false);
        //btnCancel.setBackgroundResource(R.drawable.dialog_bg_cancel_disable);
    }

    /**
     * 设置取消被点击的接口
     */
    public interface OnCancelOnclickListener {
        void onCancelClick();
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface OnConfirmOnclickListener {
        void onConfirmClick();
    }
}
