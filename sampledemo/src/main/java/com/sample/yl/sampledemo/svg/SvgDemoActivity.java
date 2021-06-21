package com.sample.yl.sampledemo.svg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.yl.mylibrary.inject.AutoWired;
import com.sample.yl.mylibrary.inject.InjectUtils;
import com.sample.yl.mylibrary.inject.InjectView;
import com.sample.yl.mylibrary.inject.OnClick;
import com.sample.yl.mylibrary.inject.OnLongClick;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.model.UserParcelable;
import com.sample.yl.sampledemo.model.UserSerializable;

import java.util.Arrays;
import java.util.List;

/**
 * 使用SVG矢量图优化图片适配，减小apk
 */
public class SvgDemoActivity extends AppCompatActivity {
    @InjectView(R.id.tv_data)
    TextView tvData;

    @AutoWired
    String name;
    @AutoWired("attr")
    String attr1;
    @AutoWired
    Boolean isBoolean = false;
    @AutoWired
    int[] array;
    @AutoWired
    UserParcelable userParcelable;
    @AutoWired
    UserParcelable[] userParcelables;
    @AutoWired("users")
    UserSerializable[] userSerializables;
    @AutoWired
    String[] strs;
    @AutoWired
    List<UserParcelable> userParcelableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_demo);
        InjectUtils.injectView(this);

        InjectUtils.injectAutoWired(this);

        InjectUtils.injectEvent(this);

        tvData.setText(this.toString());
    }

    @OnClick({R.id.tv_data, R.id.iv_svg})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_data:
                Toast.makeText(this, "数据的点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_svg:
                Toast.makeText(this, "SVG的点击事件", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnLongClick({R.id.tv_data, R.id.iv_svg})
    public boolean onViewLongClick(View view) {
        switch (view.getId()) {
            case R.id.tv_data:
                Toast.makeText(this, "数据的----长----点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_svg:
                Toast.makeText(this, "SVG的----长----点击事件", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public String toString() {
        return "SvgDemoActivity{" +
                "isBoolean=" + isBoolean +
                ", name='" + name + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", array=" + Arrays.toString(array) +
                ", userParcelable=" + userParcelable +
                ", userParcelables=" + Arrays.toString(userParcelables) +
                ", userSerializables=" + Arrays.toString(userSerializables) +
                ", strs=" + Arrays.toString(strs) +
                ", userParcelableList=" + userParcelableList +
                '}';
    }
}
