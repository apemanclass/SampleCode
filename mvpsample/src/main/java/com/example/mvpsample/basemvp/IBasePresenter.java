package com.example.mvpsample.basemvp;

/**
 * Created by jiazhui on 2017/12/7.
 * <p>
 * Presenter层基类（P层通知M层获取内容，所以封装一个getData()就够了）
 */

public interface IBasePresenter {
    void getData();
}
