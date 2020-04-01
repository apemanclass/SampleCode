package com.sample.yl.sampledemo.eventbus.noe;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.sample.yl.sampledemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${jz} on 2018/4/18。
 * 练习使用EventBus示例
 */
public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.bt_even)
    Button btEven;
    @BindView(R.id.text)
    TextView textView;

    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);//注意初始化要放在setContentView()之后


        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);//订阅者
        }

        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("测试even接收消息");
    }


    @OnClick(R.id.bt_even)
    public void onViewClicked() {

        TestEvent event = new TestEvent();
        event.setMsg("已收到事件！");
        EventBus.getDefault().post(event);//发布者

    }

    //处理接收事件的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTestEvent(TestEvent event) {

        textView.setText(event.getMsg());
        dialog.setMessage(event.getMsg());
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消EventBus的注册
        EventBus.getDefault().unregister(this);
    }

}
