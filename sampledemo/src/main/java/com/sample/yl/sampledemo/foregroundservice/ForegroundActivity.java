package com.sample.yl.sampledemo.foregroundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.sample.yl.sampledemo.R;

/**
 * 演示前台service的使用案例
 */
public class ForegroundActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        Intent intent = new Intent(this, ForegroundService.class);
        //startService(intent);
        //startForegroundService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ForegroundService.MyBinder binder = (ForegroundService.MyBinder) service;
            binder.getService();
            Logger.e("client的activity--->onServiceConnected" + ",name=" + name);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.e("client的activity--->onServiceDisconnected" + ",name=" + name);
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
