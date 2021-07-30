package com.example.retrofit2mvp.mvp;

import com.example.retrofit2mvp.http.base.BaseObserver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${jz} on 2018/10/22。
 * 创建Presenter基类
 */
public class BasePresenter<V extends MvpView> {
    private CompositeDisposable compositeDisposable;
    private V mMvpView;
    private V proxyMvpView;

//    public BasePresenter(V mvpView) {
//        this.mMvpView = mvpView;
//    }

    /**
     * 绑定View
     */
    public void attachView(V mvpView) {
        this.mMvpView = mvpView;
        //参数一：类加载器
        ClassLoader loader = mvpView.getClass().getClassLoader();
        //参数二：代理接口
        Class<?>[] interfaces = mvpView.getClass().getInterfaces();
        //参数三：方法回调
        InvocationHandler handler = new MvpViewInvocationHandler(this.mMvpView);
        proxyMvpView = (V) Proxy.newProxyInstance(loader, interfaces, handler);
    }

    private boolean isNull() {
        if (this.mMvpView == null) {
            return true;
        }
        return false;
    }

    //使用动态代理的方式，解决调用getMvpView()时重复的判断为空操作
    //统一判断--->控制对象访问权限
    private class MvpViewInvocationHandler implements InvocationHandler {

        private MvpView mvpView;

        public MvpViewInvocationHandler(MvpView view) {
            this.mvpView = view;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //检查是不是为null
            if (isNull()) {
                //如果mMvpView为null  就不用回调
                return null;
            }
            //执行回调
            return method.invoke(mvpView, args);
        }
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        this.mMvpView = null;
        removeDisposable();
    }

    /**
     * 返回 view
     */
    public V getMvpView() {
        return proxyMvpView;
//        return mMvpView;
    }

    public void addSubscription(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

    private void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}