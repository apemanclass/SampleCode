package com.sample.yl.sampledemo.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.sample.yl.sampledemo.R;

/**
 * Created by ${jz} on 2019/12/27。
 * PhotoView 是图片的展示功能控件
 */
public class PhotoViewActivity extends AppCompatActivity {
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
        }

        PhotoView photoView = findViewById(R.id.photo_view);

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)
                .into(photoView);
    }
}
