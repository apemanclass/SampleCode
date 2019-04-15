package com.example.mvpsample.basemvp;

/**
 * Created by jiazhui on 2017/12/7.
 * <p>
 * (1)首先 IDownLoadListener<String> 是啥先别管
 * <p>
 * (2)mMainPresenter实现IMainPresenter  ,实现了P层封装的getData()方法
 * <p>
 * (3)P层既指向M层又指向V层，所以P层（协调者层）M和V的实例都要有
 * <p>
 * (4)当P层接收到V层的请求后，P层在通知M层去获取数据
 */

public class mMainPresenter implements AppContracts.IMainPresenter, IDownLoadListener<String> {
    private AppContracts.IMainModel iMainModel;
    private AppContracts.IMainView iMainView;

    private mMainModel mMainModel;

    public mMainPresenter(AppContracts.IMainView iMainView) {
        this.iMainView = iMainView;
        iMainModel = new mMainModel(this);
    }

    @Override
    public void getData() {
        iMainModel.getData();
    }

    @Override
    public void downloadSuccess(String s) {
        iMainView.loadSuccess(s);
    }

    @Override
    public void downloadFail(String msg) {
        iMainView.loadFail(msg);
    }

}
