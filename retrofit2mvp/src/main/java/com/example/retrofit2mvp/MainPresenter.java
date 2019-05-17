package com.example.retrofit2mvp;

import com.example.retrofit2mvp.http.base.BaseObserver;
import com.example.retrofit2mvp.http.result.PatientEntity;
import com.example.retrofit2mvp.mvp.BaseModel;
import com.example.retrofit2mvp.mvp.BasePresenter;

import java.util.List;

/**
 * Created by ${jz} on 2018/10/23。
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView baseView) {
        //super(baseView);
        super.attachView(baseView);
    }

    public void getDatas() {
        getMvpView().showLoading();
        addSubscription(apiServer.getData(), new BaseObserver<BaseModel<List<PatientEntity>>>(getMvpView()) {

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
