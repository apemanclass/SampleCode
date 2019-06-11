package com.example.mvpsample.mvpdemo12;

import android.os.Bundle;

import com.example.mvpsample.mvpbase.BasePresenter;
import com.example.mvpsample.mvpbase.MvpView;

/**
 * Created by ${jz} on 2019/5/22。
 * 第一重代理--->目标对象
 * <p>
 * 目标对象的特点：要去实现目标接口
 */
public class ActivityMvpDelegateImpl<V extends MvpView, P extends BasePresenter<V>> implements ActivityMvpDelegate<V, P> {

    private ProxyMvpCallback12<V, P> proxyMvpCallback;

    public ActivityMvpDelegateImpl(MvpCallback12<V, P> mvpCallback) {
        this.proxyMvpCallback = new ProxyMvpCallback12<>(mvpCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.proxyMvpCallback.createPresenter();
        this.proxyMvpCallback.attachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        this.proxyMvpCallback.detachView();
    }
}
