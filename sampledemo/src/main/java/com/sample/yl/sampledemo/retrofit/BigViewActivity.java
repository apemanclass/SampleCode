package com.sample.yl.sampledemo.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sample.yl.mylibrary.customphotopreview.BigPreviewPictureView;
import com.sample.yl.sampledemo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * 图片预览的承载界面
 */
public class BigViewActivity extends AppCompatActivity {

    private BigPreviewPictureView bigView;

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        bigView = (BigPreviewPictureView) findViewById(R.id.big_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_view);
        initControls();
        try {
            InputStream inputStream = getResources().getAssets().open("qmsht.png");
            bigView.setImage(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
