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
        super(baseView);
    }

    public void getDatas() {
        addDisposable(apiServer.getData(), new BaseObserver<BaseModel<List<PatientEntity>>>(getBaseView()) {

            @Override
            public void onSuccess(BaseModel<List<PatientEntity>> o) {
                PatientEntity entity = o.getTarget().get(6);
                getBaseView().onMainSuccess(entity);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

}
