package com.sample.yl.sampledemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.jakewharton.rxbinding3.view.RxView;
import com.orhanobut.logger.Logger;
import com.sample.yl.sampledemo.androidtools.ToolsActivity;
import com.sample.yl.sampledemo.arouter.ARouterActivity;
import com.sample.yl.sampledemo.audiomanager.AudioManagerActivity;
import com.sample.yl.sampledemo.autoedittext.AutoEditTextActivity;
import com.sample.yl.sampledemo.bannerimage.BannerActivity;
import com.sample.yl.sampledemo.bottomtabbar.BottomTableBarActivity;
import com.sample.yl.sampledemo.brvah.BaseRvAdapterActivity;
import com.sample.yl.sampledemo.customview.CustomViewActivity;
import com.sample.yl.sampledemo.dragbubbleview.BubbleViewActivity;
import com.sample.yl.sampledemo.eventbus.two.EvenBusDemo;
import com.sample.yl.sampledemo.foregroundservice.ForegroundActivity;
import com.sample.yl.sampledemo.getmodleisnull.IsDataEmptyActivity;
import com.sample.yl.sampledemo.gpslocation.GpsLocActivity;
import com.sample.yl.sampledemo.imageselector.PictureSelectorActivity;
import com.sample.yl.sampledemo.immersionbar.Main2Activity;
import com.sample.yl.sampledemo.loadingdrawable.MainLoadingActivity;
import com.sample.yl.sampledemo.meeting.VideoMeetingActivity;
import com.sample.yl.sampledemo.model.UserParcelable;
import com.sample.yl.sampledemo.model.UserSerializable;
import com.sample.yl.sampledemo.popupindow.PopupActivity;
import com.sample.yl.sampledemo.progress.ProgressBarActivity;
import com.sample.yl.sampledemo.retrofit.Retrofit2Activity;
import com.sample.yl.sampledemo.retrofitdownload.RetrofitDownloadActivity;
import com.sample.yl.sampledemo.rxjava2.RxJava2Demo;
import com.sample.yl.sampledemo.shape.ShapeActivity;
import com.sample.yl.sampledemo.svg.SvgDemoActivity;
import com.sample.yl.sampledemo.textdome.TvSpanDome;
import com.sample.yl.sampledemo.timepicker.TimePickerActivity;
import com.sample.yl.sampledemo.tts.TextToSpeechActivity;
import com.sample.yl.sampledemo.utils.VersionUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

/**
 * Created by ${jz} on 2018/8/8。
 */
public class MainActivity extends BaseActivity {

    //region 界面控件声明
    @BindView(R.id.animation)
    LottieAnimationView animation;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt6)
    Button bt6;
    @BindView(R.id.bt7)
    Button bt7;
    @BindView(R.id.bt8)
    Button bt8;
    @BindView(R.id.bt9)
    Button bt9;
    @BindView(R.id.bt10)
    Button bt10;
    @BindView(R.id.bt11)
    Button bt11;
    @BindView(R.id.bt12)
    Button bt12;
    @BindView(R.id.bt13)
    Button bt13;
    @BindView(R.id.bt14)
    Button bt14;
    @BindView(R.id.bt15)
    Button bt15;
    @BindView(R.id.bt16)
    Button bt16;
    @BindView(R.id.bt17)
    Button bt17;
    @BindView(R.id.bt18)
    Button bt18;
    @BindView(R.id.bt19)
    Button bt19;
    @BindView(R.id.bt20)
    Button bt20;
    @BindView(R.id.bt21)
    Button bt21;
    @BindView(R.id.bt22)
    Button bt22;
    @BindView(R.id.bt23)
    Button bt23;
    @BindView(R.id.bt24)
    Button bt24;
    @BindView(R.id.bt25)
    Button bt25;
    @BindView(R.id.bt26)
    Button bt26;
    @BindView(R.id.bt27)
    Button bt27;
    @BindView(R.id.bt28)
    Button bt28;
    @BindView(R.id.bt29)
    Button bt29;
    //endregion

    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * ButterKnife在Activity中不需要做解绑操作，
         * 在Fragment 中必须在onDestroyView()中做解绑操作。
         */
        ButterKnife.bind(this);//注意初始化要放在setContentView()之后

        Logger.e("当前包名" + VersionUtils.getPackageName(this));

        //region handler的postDelayed()方法依附线程名
        Logger.e("UI Thread = " + Thread.currentThread().getId());
        /**
         * 这个开启的runnable会在这个handler所依附线程中运行，
         * 而这个handler是在UI线程中创建的，所以自然地依附在主线程中了。
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.e("Handler Thread = " + Thread.currentThread().getId());
            }
        }, 1000);
        //endregion

        //json动画的使用方法
        //animation.setAnimation("data.json");
        //animation.playAnimation();

        repeatClick();
    }

    //防止按钮被重复点击的方法
    private void repeatClick() {
        // 创建观察者
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                Logger.d("ClickActivity:点击了按钮");
                gotoActivity(BannerActivity.class);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // 绑定按钮的点击事件，并绑定观察者与被观察者
        RxView.clicks(bt1)
                //// 设置间隔2秒才能发送下一个事件
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(observer);

        RxView.clicks(bt2)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt10,
            R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15, R.id.bt16, R.id.bt17, R.id.bt18, R.id.bt19,
            R.id.bt20, R.id.bt21, R.id.bt22, R.id.bt23, R.id.bt24, R.id.bt25, R.id.bt26, R.id.bt27, R.id.bt28, R.id.bt29})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                //gotoActivity(BannerActivity.class);
                break;
            case R.id.bt2:
                gotoActivity(TimePickerActivity.class);
                break;
            case R.id.bt3:
                gotoActivity(BottomTableBarActivity.class);
                break;
            case R.id.bt4:
                gotoActivity(TvSpanDome.class);
                break;
            case R.id.bt5:
                gotoActivity(Main2Activity.class);
                break;
            case R.id.bt6:
                gotoActivity(MainLoadingActivity.class);
                break;
            case R.id.bt7:
                gotoActivity(PictureSelectorActivity.class);
                break;
            case R.id.bt8:
                gotoActivity(ToolsActivity.class);
                break;
            case R.id.bt9:
                gotoActivity(GpsLocActivity.class);
                break;
            case R.id.bt10:
                //gotoActivity(EventBusActivity.class);
                gotoActivity(EvenBusDemo.class);
                break;
            case R.id.bt11:
                gotoActivity(IsDataEmptyActivity.class);
                break;
            case R.id.bt12:
                gotoActivity(BubbleViewActivity.class);
                break;
            case R.id.bt13:
                gotoActivity(PopupActivity.class);
                break;
            case R.id.bt14:
                gotoActivity(AudioManagerActivity.class);
                break;
            case R.id.bt15:
                gotoActivity(BaseRvAdapterActivity.class);
                break;
            case R.id.bt16:
                gotoActivity(Retrofit2Activity.class);
                break;
            case R.id.bt17:
                gotoActivity(RetrofitDownloadActivity.class);
                break;
            case R.id.bt18:
                gotoActivity(ProgressBarActivity.class);
                break;
            case R.id.bt19:
                gotoActivity(RxJava2Demo.class);
                break;
            case R.id.bt20:
                gotoActivity(TextToSpeechActivity.class);
                break;
            case R.id.bt21:
                gotoActivity(AutoEditTextActivity.class);
                break;
            case R.id.bt22:
                gotoActivity(SvgDemoActivity.class);
                break;
            case R.id.bt23:
                gotoActivity(CustomViewActivity.class);
                break;
            case R.id.bt24:
                gotoActivity(ForegroundActivity.class);
                break;
            case R.id.bt25:
                // 在AndroidManifest.xml文件下添加android:sharedUserId=”android.uid.system” 。
                PowerManager pManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                pManager.reboot("重启");
                break;
            case R.id.bt26:
                gotoActivity(ARouterActivity.class);
                break;
            case R.id.bt27:
                gotoActivityData();
                break;
            case R.id.bt28:
                gotoActivity(VideoMeetingActivity.class);
                break;
            case R.id.bt29:
                gotoActivity(ShapeActivity.class);
                break;
        }
    }

    private void gotoActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    //跳转app自动绑定参数值
    private void gotoActivityData() {
        ArrayList<UserParcelable> userParcelableList = new ArrayList<>();
        userParcelableList.add(new UserParcelable("Jett"));
        Intent intent = new Intent(this, SvgDemoActivity.class)
                .putExtra("name", "jz")
                .putExtra("attr", "帅")
                .putExtra("isBoolean", true)
                .putExtra("array", new int[]{1, 2, 3, 4, 5, 6})
                .putExtra("userParcelable", new UserParcelable("Lance"))
                .putExtra("userParcelables", new UserParcelable[]{new UserParcelable("Alvin")})
                .putExtra("users", new UserSerializable[]{new UserSerializable("Jett")})
                .putExtra("strs", new String[]{"1", "2"})
                .putParcelableArrayListExtra("userParcelableList", userParcelableList);
        startActivity(intent);
    }

    /**
     * 双击back键退出应用程序
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
            Process.killProcess(Process.myPid());
        }
    }


}
