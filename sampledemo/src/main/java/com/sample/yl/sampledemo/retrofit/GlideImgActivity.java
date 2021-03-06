package com.sample.yl.sampledemo.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.sample.yl.sampledemo.MyApplication;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.retrofit.entity.ImgEntity;
import com.sample.yl.sampledemo.retrofit.entity.ImgWeal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
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

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_img);
        ButterKnife.bind(this);
        mContext = this;

        initHttp();
        rvImg.setLayoutManager(new LinearLayoutManager(this));
    }

    public interface ImgService {
        @GET("data/%E7%A6%8F%E5%88%A9/1000/1")
        Observable<ImgEntity<List<ImgWeal>>> getWeal();
    }

    private void initHttp() {
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/api/")
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
                    public void onNext(final ImgEntity<List<ImgWeal>> listImgEntity) {
                        final List<ImgWeal> list = listImgEntity.results;

                        final GlideImgAdapter imgAdapter = new GlideImgAdapter(R.layout.item_glide_img, list, GlideImgActivity.this);
                        imgAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                                MyApplication.imgBean = list;

                                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("position", position);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        rvImg.setAdapter(imgAdapter);
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
