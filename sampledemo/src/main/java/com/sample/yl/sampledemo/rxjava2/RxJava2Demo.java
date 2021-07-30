package com.sample.yl.sampledemo.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sample.yl.sampledemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${jz} on 2018/10/25。
 * 练习RxJava2的使用
 */
public class RxJava2Demo extends AppCompatActivity {
    private static final String BASEURL = "www,baidu.com";
    private String TAG = "TAG";
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2_demo);

        rxJava2Demo1();
        //rxJava2Thread();
        //rxJava2FlatMap();
        //rxJava2ConcatMap();
    }

    /**
     * 简单的RxJava2链式触发流程
     */
    private void rxJava2Demo1() {

        Observable.create(new ObservableOnSubscribe<Integer>() {

            /**
             * Emitter是发射器的意思
             * @param emitter 这个就是用来发出事件的，它可以发出三种类型的事件.
             *                分别是next事件、complete事件和error事件。
             */
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                return objectObservable.delay(5, TimeUnit.SECONDS);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, integer + "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        });
    }


    /**
     * RxJava2的被观察者和观察者线程调度的问题
     * <p>
     * 我们更多想要的是这么一种情况, 在子线程中做耗时的操作, 然后回到主线程中来操作UI。
     * <p>
     * 要达到这个目的, 我们需要先改变上游发送事件的线程, 让它去子线程中发送事件,
     * 然后再改变下游的线程, 让它去主线程接收事件. 通过RxJava内置的线程调度器可以很轻松的做到这一点.
     * 接下来看一段代码:
     */
    private void rxJava2Thread() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.e(TAG, "emitter 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

        /**
         *subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
         *
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
         *
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
         *
         * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
         * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
         * Schedulers.newThread() 代表一个常规的新线程
         * AndroidSchedulers.mainThread() 代表Android的主线程
         */
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }


    /**
     * Retrofit与RxJava的结合使用
     */
    private void rxJava2AndRetrofitDemo() {
        LoginResponse request = new LoginResponse();
        request.setLogin("登录");

        Api api = RxJava2Demo.createRt().create(Api.class);
        api.login(request)
                .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//回到主线程去处理请求结果
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);//得到一个Disposable时就调用
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJava2Demo.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RxJava2Demo.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                });

        /**
         *如果在请求的过程中Activity已经退出了, 这个时候如果回到主线程去更新UI, 那么APP肯定就崩溃了。
         *
         * 在此使用rxjava中的Disposable.dispose()方法来阻断发生的线程改变UI
         *
         * 如果有多个Disposable 该怎么办呢, RxJava中已经内置了一个容器CompositeDisposable,
         * 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中, 在退出的时候,
         * 调用CompositeDisposable.clear() 即可切断所有的水管.
         */
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.clear();
        }
    }

    private static Retrofit createRt() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        return new Retrofit.Builder().baseUrl(BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，
     * 然后将它们发射的事件合并后放进一个单独的Observable里.
     * <p>
     * 上游每发送一个事件, flatMap都将创建一个新的水管, 然后发送转换之后的新的事件, 下游接收到的就是这些新的水管发送的数据.
     * 这里需要注意的是, flatMap并不保证事件的顺序
     */
    private void rxJava2FlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }

    /**
     * concatMap和flatMap的作用几乎一模一样, 只是它的结果是严格按照上游发送的顺序来发送的,
     */
    private void rxJava2ConcatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }

    /**
     * Retrofit与RxJava的结合使用map
     * 解决嵌套请求问题
     */
    @SuppressLint("CheckResult")
    private void rxJava2AndRetrofitFlatMap() {
        final Api api = RxJava2Demo.createRt().create(Api.class);
        api.register(new RegisterResponse())    //1: 发起注册请求
                .subscribeOn(Schedulers.io())   //给上面切换异步 io线程 在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //给下面切换 主线程  回到主线程去处理请求注册结果
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        //2: 先根据注册的响应结果去做一些操作
                    }
                })
                .observeOn(Schedulers.io())   //3: 给下面切换异步 io线程   回到IO线程去发起登录请求
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        return api.login(new LoginResponse());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  //4: 给下面切换 主线程 回到主线程去处理请求登录的结果
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        Toast.makeText(RxJava2Demo.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(RxJava2Demo.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

