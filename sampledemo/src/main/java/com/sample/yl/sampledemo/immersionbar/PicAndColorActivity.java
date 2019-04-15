package com.sample.yl.sampledemo.immersionbar;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.sample.yl.sampledemo.R;

/**
 * 图片状态栏加彩色导航栏
 */
public class PicAndColorActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    Toolbar toolbar;
    SeekBar seekBar;
    Button bt1, bt2, bt3;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_pic_and_color;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.top_view)
                .navigationBarColor(R.color.colorPrimary)
                .fullScreen(true)
                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
                .init();
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        bt1 = (Button) findViewById(R.id.btn_status_color);
        bt2 = (Button) findViewById(R.id.btn_navigation_color);
        bt3 = (Button) findViewById(R.id.btn_color);
    }

    @Override
    protected void setListener() {
        seekBar.setOnSeekBarChangeListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_status_color:
                mImmersionBar.statusBarColor(R.color.colorAccent).init();
                break;
            case R.id.btn_navigation_color:
                if (ImmersionBar.hasNavigationBar(this))
                    mImmersionBar.navigationBarColor(R.color.colorAccent).init();
                else
                    Toast.makeText(this, "当前设备没有导航栏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_color:
                mImmersionBar.getTag("PicAndColor").init(); //根据tag标记来恢复
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float alpha = (float) progress / 100;
        mImmersionBar.statusBarColorTransform(R.color.colorAccent)
                .navigationBarColorTransform(R.color.colorDef)
                .addViewSupportTransformColor(toolbar)
                .barAlpha(alpha)
                .init();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
