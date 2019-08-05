package com.sample.yl.sampledemo.foregroundservice;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import com.orhanobut.logger.Logger;
import com.sample.yl.sampledemo.R;

public class ForegroundService extends Service {
    private String channelId = "foreground";
    private String channelName = "前台服务";

    public class MyBinder extends Binder {
        public ForegroundService getService() {
            return ForegroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.e("前台服务--->onBind");
        return new MyBinder();
    }


    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("前台服务--->onCreate");

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_MIN);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
        Notification notification = new Notification.Builder(this, channelId)
                .setContentTitle("前台服务")
                .setContentText("连接主机服务运行中。。。")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("前台服务--->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.e("前台服务--->onDestroy");
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知
        super.onDestroy();
    }
}
