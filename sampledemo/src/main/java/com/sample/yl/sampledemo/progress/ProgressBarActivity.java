package com.sample.yl.sampledemo.progress;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sample.yl.sampledemo.R;

/**
 * 演示自定义进度条的使用案例
 */
public class ProgressBarActivity extends AppCompatActivity {
    private CustomizedProgressBar pgb;
    private int integrity = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        pgb = (CustomizedProgressBar) findViewById(R.id.pgb);

        pgb.setMaxCount(100);
        pgb.setCurrentCount(integrity);

        handler.postDelayed(runnable, 1000);
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (integrity != 0) {
                integrity = integrity - 5;
            } else {
                handler.removeCallbacks(runnable);
            }

            pgb.setCurrentCount(integrity);

            handler.postDelayed(this, 1000);
        }
    };

}
