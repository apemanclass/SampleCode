package com.example.firstcode.chapter2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.firstcode.R;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ThirdActivity", "Task id is " + getTaskId());
        setContentView(R.layout.activity_third);

        Button button3 = (Button) findViewById(R.id.button_3);

        Toast.makeText(ThirdActivity.this, "栈内活动共" + ActivityCollector.activities.size() + "个", Toast.LENGTH_SHORT).show();

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}
