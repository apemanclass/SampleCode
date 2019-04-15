package com.example.mvpsample.basemvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvpsample.R;

/**
 * (1)MianActivity实现IMainView接口，实现如下两个方法
 * <p>
 * (2)View层是指向Presenter层的，所以必须拿到P层实例，用于通知P层快去帮我找数据
 */

public class MainActivity extends AppCompatActivity implements AppContracts.IMainView {
    private mMainPresenter mainPresenter;
    private Button bt;

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        bt = (Button) findViewById(R.id.bt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();

        mainPresenter = new mMainPresenter(this);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.getData();//V层需要数据了， 叫P层快去找
            }
        });
    }

    @Override
    public void loadSuccess(String s) {
        Toast.makeText(this, "获取成功：" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFail(String err) {

    }
}
