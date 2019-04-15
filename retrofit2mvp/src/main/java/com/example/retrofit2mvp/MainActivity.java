package com.example.retrofit2mvp;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofit2mvp.http.result.PatientEntity;

/**
 * Created by ${jz} on 2018/10/23。
 * https://www.jianshu.com/p/df4eee78085c   资料链接
 * <p>
 * MVP+Retrofit2+okHttp3+RxJava2网络请求框架
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    private TextView tvMsg;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        tvMsg = (TextView) findViewById(R.id.tv_msg);

        mPresenter.getDatas();
    }


    @Override
    public void onMainSuccess(PatientEntity entity) {
        tvMsg.setText(entity.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
