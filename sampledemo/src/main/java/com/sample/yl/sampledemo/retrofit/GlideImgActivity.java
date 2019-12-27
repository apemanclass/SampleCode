package com.sample.yl.sampledemo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.retrofit.entity.ImgEntity;
import com.sample.yl.sampledemo.retrofit.entity.ImgWeal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class GlideImgActivity extends AppCompatActivity {

    @BindView(R.id.rv_img)
    RecyclerView rvImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_img);
        ButterKnife.bind(this);

        initHttp();
        rvImg.setLayoutManager(new LinearLayoutManager(this));
    }

    public interface ImgService {
        @GET("data/%E7%A6%8F%E5%88%A9/1000/1")
        Observable<ImgEntity<List<ImgWeal>>> getWeal();
    }

    private void initHttp() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ImgService imgService = retrofit.create(ImgService.class);
        imgService.getWeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgEntity<List<ImgWeal>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImgEntity<List<ImgWeal>> listImgEntity) {
                        List<ImgWeal> list = listImgEntity.results;

                        GlideImgAdapter adapter = new GlideImgAdapter(R.layout.item_glide_img, list, GlideImgActivity.this);
                        rvImg.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
