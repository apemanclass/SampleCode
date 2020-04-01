package com.sample.yl.sampledemo.immersionbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sample.yl.sampledemo.R;

/**
 * 导航栏结合约束布局的使用
 */
public class CoordinatorActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView text;
    private FloatingActionButton fab;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_coordinator;
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        toolbar = findViewById(R.id.detail_toolbar);
        text = findViewById(R.id.text);
        fab = findViewById(R.id.fab);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
    }

    @Override
    protected void initView() {
        initControls();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "我是Snackbar", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
        text.setText("关于Snackbar在4.4和emui3.1上高度显示不准确的问题是由于沉浸式使用了系统的" +
                "WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS或者WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION" +
                "属性造成的，目前尚不知有什么解决办法");

    }

    @Override
    protected void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
