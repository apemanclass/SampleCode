package com.example.mvpsample.mvpdemo12;


import com.example.mvpsample.mvpbase.BaseModel;
import com.example.mvpsample.mvpbase.BaseObserver;
import com.example.mvpsample.mvpbase.BasePresenter;
import com.example.mvpsample.mvpbase.PatientEntity;

import java.util.List;

/**
 * Created by ${jz} on 2018/10/23。
 */
public class MainPresenter12 extends BasePresenter<MainView12> {

//    public MainPresenter12(MainView12 baseView) {
//        super.attachView(baseView);
//    }

    public void getDatas() {
        getMvpView().showLoading();
        addSubscription(apiServer.getData(), new BaseObserver<BaseModel<List<PatientEntity>>>() {

            @Override
            public void onSuccess(BaseModel<List<PatientEntity>> o) {
                getMvpView().hideLoading();
                PatientEntity entity = o.getTarget().get(6);
                getMvpView().onMainSuccess(entity);
            }

            @Override
            public void onError(String msg) {
                //getMvpView().hideLoading();
            }
        });
    }

}
