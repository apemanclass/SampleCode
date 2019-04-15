package com.sample.yl.sampledemo.getmodleisnull;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.sample.yl.sampledemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${jz} on 2018/4/23。
 * 判断服务器数据字段null时出现的空指针异常。
 * <p>
 * 资料链接：
 * https://mp.weixin.qq.com/s/RWQ6vb9vUa_XeFZMI2-wow
 */
public class IsDataEmptyActivity extends AppCompatActivity {

    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;


    private JavaBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_data_empty);
        ButterKnife.bind(this);


        bean = new JavaBean();
        bean.setStr("哈哈哈哈哈");
        bean.setName(null);
    }


    @OnClick(R.id.bt_get)
    public void onViewClicked() {

        tv.setText(bean.getStr());

        tv2.setText(bean.getName());

        tv3.setText(convertTime(22));
    }


    /**
     * convert time str
     *
     * @param time
     * @return
     */
    public String convertTime(int time) {

        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }
}
