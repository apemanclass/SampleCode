package com.example.mvpsample.basemvp;

/**
 * Created by jiazhui on 2017/12/8.
 * <p>
 * (1)泛型既为你请求到的数据
 * <p>
 * (2)因为大多数网络请求框架都有成功回调 和 失败回调，所以IDownLoadListener封装了成功和失败两个方法。
 */

public interface IDownLoadListener<T> {

    void downloadSuccess(T t);

    void downloadFail(String msg);
}
