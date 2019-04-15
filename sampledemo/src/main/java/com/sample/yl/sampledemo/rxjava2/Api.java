package com.sample.yl.sampledemo.rxjava2;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by ${jz} on 2018/11/3。
 */
public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginResponse request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterResponse request);

}