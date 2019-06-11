package com.example.mvpsample.mvpdemo12;

import com.example.mvpsample.mvpbase.BasePresenter;
import com.example.mvpsample.mvpbase.MvpView;

/**
 * Created by ${jz} on 2019/5/23。
 * 第一重代理--->目标接口
 */
public interface MvpCallback12<V extends MvpView, P extends BasePresenter<V>> {
    //定义：关于MVP的方法-->规范

    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();
}
