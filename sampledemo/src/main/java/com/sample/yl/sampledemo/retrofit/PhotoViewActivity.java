package com.sample.yl.sampledemo.retrofit;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.sample.yl.sampledemo.MyApplication;
import com.sample.yl.sampledemo.R;

/**
 * Created by ${jz} on 2019/12/27。
 * PhotoView 是图片的展示功能控件
 */
public class PhotoViewActivity extends AppCompatActivity {
    private int position = 0;
    private ViewPager vp;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
        }

        vp = findViewById(R.id.vp);


        if (MyApplication.imgBean != null) {
            adapter = new MyPagerAdapter(this, MyApplication.imgBean);
//            vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
            vp.setAdapter(adapter);
            vp.setCurrentItem(position);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (MyApplication.imgBean != null) {
            MyApplication.imgBean = null;
        }
    }
}
