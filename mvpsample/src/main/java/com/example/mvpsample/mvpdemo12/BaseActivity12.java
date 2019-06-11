package com.example.mvpsample.mvpdemo12;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mvpsample.mvpbase.BaseModel;
import com.example.mvpsample.mvpbase.BasePresenter;
import com.example.mvpsample.mvpbase.MvpView;


/**
 * Created by ${jz} on 2018/10/23。
 * activity基类
 * <p>
 * 第一重代理--->代理对象-->BaseActivity12
 * 特点：
 * 1：实现目标接口（可以不需要，可有可无）
 * 2：持有目标对象的引用（必须的）
 * <p>
 * 第二重代理--->目标对象-->BaseActivity12
 * 目标对象的特点：要去实现目标接口
 */
public abstract class BaseActivity12<V extends MvpView, P extends BasePresenter<V>> extends AppCompatActivity implements MvpView, MvpCallback12<V, P> {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;


    //持有目标对象--->创建一个
    private ActivityMvpDelegateImpl delegateImpl;

    private ActivityMvpDelegateImpl<V, P> getDelegateImpl() {
        if (delegateImpl == null) {
            delegateImpl = new ActivityMvpDelegateImpl<V, P>(this);
        }
        return delegateImpl;
    }

    private P presenter;

    @Override
    public P createPresenter() {
        return this.presenter;
    }

    @Override
    public P getPresenter() {
        return this.presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    //回调生命周期--->控制对象访问
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        getDelegateImpl().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getDelegateImpl().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        getDelegateImpl().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getDelegateImpl().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        getDelegateImpl().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        getDelegateImpl().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getDelegateImpl().onDestroy();
    }

    @Override
    public void showLoading() {
        Toast.makeText(mContext, "加载中。。。", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(mContext, "加载成功。。。", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

}

