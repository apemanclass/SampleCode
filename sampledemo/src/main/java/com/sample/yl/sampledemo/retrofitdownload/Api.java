package com.sample.yl.sampledemo.retrofitdownload;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * Created by ${jz} on 2018/8/30。
 */
public interface Api {

    @Streaming//注明为流文件，防止retrofit将大文件读入内存
    @GET()
    Observable<ResponseBody> download(@Url String url);//通过@Url覆盖baseurl
}
