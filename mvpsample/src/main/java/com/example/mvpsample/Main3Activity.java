package com.example.mvpsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvpsample.mvpbase.PatientEntity;
import com.example.mvpsample.mvpdemo12.BaseActivity12;
import com.example.mvpsample.mvpdemo12.MainPresenter12;
import com.example.mvpsample.mvpdemo12.MainView12;

public class Main3Activity extends BaseActivity12<MainView12, MainPresenter12> implements MainView12 {

    private Button btnGet;
    private TextView tvResults;

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        btnGet = (Button) findViewById(R.id.btn_get);
        tvResults = (TextView) findViewById(R.id.tv_results);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getDatas();
            }
        });
    }

    @Override
    public MainPresenter12 createPresenter() {
        return new MainPresenter12();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initControls();
    }

    @Override
    public void onMainSuccess(PatientEntity entity) {
        tvResults.setText(entity.toString());
    }

}
