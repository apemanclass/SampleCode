package com.example.mvpsample.basemvp;

/**
 * Created by jiazhui on 2017/12/7.
 * <p>
 * AppContracts(用于统一接口管理，请求的是简单的String)
 * 项目中所有的M.V.P单独接口都可以写在这里面，方便管理
 * <p>
 * 每一个请求数据的模块都要定义好M层 V层 P层接口
 * 其中V层中的泛型是你请求的数据类型
 */

public interface AppContracts {
    /**
     * MainActivity定义的M层 V层 P层
     */

    interface IMainView extends IBaseView<String> {

    }

    interface IMainModel extends IBaseModel {

    }

    interface IMainPresenter extends IBasePresenter {

    }
}
