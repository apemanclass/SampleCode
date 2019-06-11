package com.example.mvpsample.mvpdemo12;

import android.os.Bundle;

import com.example.mvpsample.mvpbase.BasePresenter;
import com.example.mvpsample.mvpbase.MvpView;

/**
 * Created by ${jz} on 2019/5/22。
 * 第一重代理--->目标接口
 */
public interface ActivityMvpDelegate<V extends MvpView, P extends BasePresenter<V>> {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
