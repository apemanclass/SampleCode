package com.example.mvpsample.mvpdemo12;

import com.example.mvpsample.mvpbase.BasePresenter;
import com.example.mvpsample.mvpbase.MvpView;

/**
 * Created by ${jz} on 2019/5/23。
 * 第二重代理--->代理对象
 * 特点：
 * 1：实现目标接口（可以不需要，可有可无）
 * 2：持有目标对象的引用（必须的）
 */
public class ProxyMvpCallback12<V extends MvpView, P extends BasePresenter<V>> implements MvpCallback12<V, P> {

    //持有目标对象的引用
    private MvpCallback12<V, P> mvpCallback;

    public ProxyMvpCallback12(MvpCallback12<V, P> mvpCallback) {
        this.mvpCallback = mvpCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = mvpCallback.getPresenter();
        if (presenter == null) {
            presenter = mvpCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter不能为空");
        }
        mvpCallback.setPresenter(presenter);
        return presenter;
    }

    @Override
    public P getPresenter() {
        P presenter = mvpCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("presenter不能为空");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mvpCallback.setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        V view = mvpCallback.getMvpView();
        if (view == null) {
            throw new NullPointerException("view不能为空");
        }
        return view;
    }

    public void attachView() {
        getPresenter().attachView(getMvpView());
    }

    public void detachView() {
        getPresenter().detachView();
    }
}
