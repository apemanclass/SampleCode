package com.sample.yl.sampledemo.retrofitdownload;

import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${jz} on 2019/3/29。
 * 使用retrofit做文件上传示例
 */
public class UploadFileDemo {

    interface Api {
        @Multipart
        @POST("xjxx/uploadAudio/{code}")
        Observable<BaseEntity> uploadMissionWav(@Path("code") String code, @Part MultipartBody.Part file);
    }

//    public void uploadAudioFile(String code, File file) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//
//        addSubscription(Api.uploadMissionWav(code, part), new MyObserver<BaseEntity>() {
//            @Override
//            public void onSuccess(BaseEntity model) {
//                Toast.makeText(mContext, "上传成功", Toast.LENGTH_LONG).show();
//                LogUtil.e("onSuccess=" + model.getText());
//                getMvpView().uploadSuccess();
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                Toast.makeText(mContext, "上传失败", Toast.LENGTH_LONG).show();
//                LogUtil.e("onFailure=" + msg);
//                getMvpView().uploadSuccess();
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//    }

}
