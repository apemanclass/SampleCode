package com.example.retrofit2mvp.http;


import com.example.retrofit2mvp.http.result.PatientEntity;
import com.example.retrofit2mvp.mvp.BaseModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by ${jz} on 2018/10/22。
 */
public interface ApiServer {

    @POST("inhosp/getData")
    Observable<BaseModel<List<PatientEntity>>> getData();
}
