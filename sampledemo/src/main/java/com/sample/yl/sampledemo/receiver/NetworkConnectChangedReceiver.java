package com.sample.yl.sampledemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sample.yl.mylibrary.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ${jz} on 2018/8/8。
 * <p>
 * 实现监听网络状态变更的广播接收器
 */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //判断当前的网络连接状态是否可用
        boolean isConnected = NetUtils.isConnected(context);
        Log.d("TAG", "onReceive: 当前网络 " + isConnected);
        EventBus.getDefault().post(new NetworkChangeEvent(isConnected));

    }
}
