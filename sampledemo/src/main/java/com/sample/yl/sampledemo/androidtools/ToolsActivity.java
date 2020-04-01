package com.sample.yl.sampledemo.androidtools;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sample.yl.sampledemo.R;

public class ToolsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView oncall_hands_free;
    private ImageView oncall_mute;

    int flag1 = 0;
    int flag2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        initControls();
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        oncall_hands_free = (ImageView) findViewById(R.id.oncall_hands_free);
        oncall_mute = (ImageView) findViewById(R.id.oncall_mute);

        oncall_hands_free.setOnClickListener(this);
        oncall_mute.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oncall_hands_free:
                if (flag1 == 0) {
                    oncall_hands_free.setSelected(true);
                    flag1 = 1;
                } else {
                    oncall_hands_free.setSelected(false);
                    flag1 = 0;
                }
                break;
            case R.id.oncall_mute:
                if (flag2 == 0) {
                    oncall_mute.setSelected(true);
                    flag2 = 1;
                } else {
                    oncall_mute.setSelected(false);
                    flag2 = 0;
                }
                break;
        }
    }

}
