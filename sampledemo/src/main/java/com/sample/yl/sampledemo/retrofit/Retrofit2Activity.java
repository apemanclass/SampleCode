package com.sample.yl.sampledemo.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.retrofit.entity.Blog;
import com.sample.yl.sampledemo.retrofit.entity.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class Retrofit2Activity extends AppCompatActivity {
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.bt_img)
    Button btImg;
    @BindView(R.id.bt_big_img)
    Button btBigImg;

    @OnClick(R.id.bt_img)
    public void onViewClicked() {
        startActivity(new Intent(this, GlideImgActivity.class));
    }

    @OnClick(R.id.bt_big_img)
    public void onViewClicked2() {
        startActivity(new Intent(this, BigViewActivity.class));
    }

    public interface BlogService {
        @GET("blog/{id}")
        Call<Result<Blog>> getBlog(@Path("id") int id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        ButterKnife.bind(this);

        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.14:4567/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BlogService service = retrofit.create(BlogService.class);

        Call<Result<Blog>> resultCall = service.getBlog(8);
        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        resultCall.enqueue(new Callback<Result<Blog>>() {
            @Override
            public void onResponse(Call<Result<Blog>> call, Response<Result<Blog>> response) {
                // 已经转换为想要的类型了
                Result<Blog> result = response.body();
                tvMsg.setText(result.toString());
            }

            @Override
            public void onFailure(Call<Result<Blog>> call, Throwable t) {

            }
        });

    }
}
