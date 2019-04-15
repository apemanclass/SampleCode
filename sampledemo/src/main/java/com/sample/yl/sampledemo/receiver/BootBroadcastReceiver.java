package com.sample.yl.sampledemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sample.yl.sampledemo.MainActivity;

/**
 * Created by ${jz} on 2018/6/7。
 * <p>
 * 监测开机自启APP的静态广播的使用方法
 */
public class BootBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TAG", "BootReceiver.onReceive: " + intent.getAction());
        System.out.println("自启动程序即将执行");

        //MainActivity就是开机显示的界面
        Intent mBootIntent = new Intent(context, MainActivity.class);
        //下面这句话必须加上才能开机自动运行app的界面
        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mBootIntent);
    }

}
