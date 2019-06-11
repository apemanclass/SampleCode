package com.example.mvpsample.mvpbase;


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
