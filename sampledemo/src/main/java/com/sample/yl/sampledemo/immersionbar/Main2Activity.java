package com.sample.yl.sampledemo.immersionbar;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.sample.yl.sampledemo.R;

import java.util.ArrayList;

/**
 * 沉浸式状态栏和导航栏效果示例
 */
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button bt1, bt2, bt3;
    private Spinner spinner_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initControls();
        regListener();

        ArrayList data_list = new ArrayList<String>();
        data_list.add("心电一科");
        data_list.add("心电一科2");
        data_list.add("心电一科3");
        data_list.add("心电一科4");

        //适配器
        ArrayAdapter arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner_content.setAdapter(arr_adapter);
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        spinner_content = (Spinner) findViewById(R.id.spinner_content);
    }

    /**
     * 注册组件的监听
     */
    private void regListener() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                gotoActivity(BackActivity.class);
                break;
            case R.id.bt2:
                gotoActivity(CoordinatorActivity.class);
                break;
            case R.id.bt3:
                gotoActivity(PicAndColorActivity.class);
                break;
        }
    }


    private void gotoActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
