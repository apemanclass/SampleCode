package com.example.mvpsample.basemvp;

/**
 * Created by jiazhui on 2017/12/7.
 * <p>
 * View层基类（loadSuccess和loadFail用于接收请求返回的消息，T为任意需要请求的数据类型）
 */
public interface IBaseView<T> {

    void loadSuccess(T t);

    void loadFail(String err);

}
