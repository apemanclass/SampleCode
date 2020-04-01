package com.example.firstcode;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.firstcode.chapter2.FirstActivity;
import com.example.firstcode.chapter3.CustomUiActivity;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt1, bt2, bt3, bt4, bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        regListener();
    }


    /**
     * 初始化布局的控件
     */
    private void initControls() {
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
    }

    /**
     * 注册组件的监听
     */
    private void regListener() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                gotoActivity(FirstActivity.class);
                break;
            case R.id.bt2:
                gotoActivity(CustomUiActivity.class);
                break;
            case R.id.bt3:

                break;
            case R.id.bt4:

                break;
            case R.id.bt5:

                break;
        }
    }

    private void gotoActivity(Class cls) {
        Intent intent = new Intent(this, cls);

        startActivity(intent);
    }
}
