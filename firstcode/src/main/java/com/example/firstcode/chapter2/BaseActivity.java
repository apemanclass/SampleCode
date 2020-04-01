package com.example.firstcode.chapter2;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jiazhui on 2018/1/3.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在父类中打印如下：每当我们进入到一个活动的界面，就会打印出该活动的类名，这样做我们就会时时刻刻知晓当前手机界面对应的是哪一个活动
        Log.e("BaseActivity", getClass().getSimpleName());
        Toast.makeText(this, "BaseActivity：" + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
