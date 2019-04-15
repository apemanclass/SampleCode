package com.sample.yl.sampledemo.eventbus.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.sample.yl.sampledemo.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.bt_send)
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        MessageEvent msg = new MessageEvent();
        msg.setMsg("我是第二个界面发送的消息！");
        EventBus.getDefault().post(msg);//发布者
        finish();
    }
}
