package com.example.retrofit2mvp;

import com.example.retrofit2mvp.http.result.PatientEntity;
import com.example.retrofit2mvp.mvp.BaseView;

/**
 * Created by ${jz} on 2018/10/23。
 */
public interface MainView extends BaseView {

    void onMainSuccess(PatientEntity entity);

}
