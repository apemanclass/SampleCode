package com.example.firstcode.chapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firstcode.R;
import com.sample.yl.mylibrary.customhead.TitleLayout;

/**
 * 使用封装自定义title控件的示例
 */
public class CustomUiActivity extends AppCompatActivity {
    private TitleLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initControls();


        title.setTitle("封装头部布局");
        //title.setMoreVisible(View.INVISIBLE);
        title.setMoreClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomUiActivity.this, "在activity中调用自定义布局中的事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        title = (TitleLayout) findViewById(R.id.title);
    }
}
