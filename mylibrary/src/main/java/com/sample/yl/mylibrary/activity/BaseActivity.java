package com.sample.yl.mylibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sample.yl.mylibrary.R;

/**
 * Created by jz on 2017/5/3.
 * 本类封装activity常用的方法
 * 1、通用的头布局样式及头布局的点击事件
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout viewContent;
    private TextView tvTitle;

    OnClickListener onClickListenerTopLeft;
    OnClickListener onClickListenerTopRight;

    private int menuResId;//icon 图标的ID
    private String menuStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewContent = (FrameLayout) findViewById(R.id.viewContent);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        //将继承 BaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(getContentView(), viewContent);

        //初始化设置 Toolbar
        setSupportActionBar(toolbar);
        //设置不显示自带的 title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initView(savedInstanceState);
    }

    public interface OnClickListener {
        void onClick();
    }

    /**
     * 返回一个布局的layout资源
     */
    protected abstract int getContentView();

    /**
     * 初始化控件ID及一些配置
     */
    protected abstract void initView(Bundle savedInstanceState);


    /**
     * 设置头部中间title的文字
     *
     * @param title 头布局的文字
     */
    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置头布局左边的button图片及点击事件
     *
     * @param iconResId       图片资源的ID
     * @param onClickListener 点击事件
     */
    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener) {
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

    /**
     * 设置头布局右边的button图片、文字及点击事件
     *
     * @param menuStr         文字
     * @param onClickListener 点击事件
     */
    protected void setTopRightButton(String menuStr, OnClickListener onClickListener) {
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    /**
     * 设置头布局右边的button图片、文字及点击事件
     *
     * @param menuStr         文字
     * @param menuResId       图片资源的ID
     * @param onClickListener 点击事件
     */
    protected void setTopRightButton(String menuStr, int menuResId, OnClickListener onClickListener) {
        this.menuResId = menuResId;
        this.menuStr = menuStr;
        this.onClickListenerTopRight = onClickListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_base_activity_top_bar, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onClickListenerTopLeft.onClick();
        } else if (item.getItemId() == R.id.menu_1) {
            onClickListenerTopRight.onClick();
        }
        return true; // true 告诉系统我们自己处理了点击事件
    }
}
