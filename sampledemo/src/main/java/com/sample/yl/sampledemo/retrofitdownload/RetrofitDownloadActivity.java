package com.sample.yl.sampledemo.retrofitdownload;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.orhanobut.logger.Logger;
import com.sample.yl.mylibrary.utils.PermissionUtils;
import com.sample.yl.sampledemo.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RetrofitDownloadActivity extends AppCompatActivity {

    @BindView(R.id.bt_download)
    Button btDownload;
    @BindView(R.id.bt_stop)
    Button btStop;
    @BindView(R.id.bt_clear)
    Button btClear;
    @BindView(R.id.bt_setting)
    Button btSetting;

    //要下载的文件地址
    private String url = "https://qd.myapp.com/myapp/qqteam/Androidlite/qqlite_3.7.1.704_android_r110206_GuanWang_537057973_release_10000484.apk";
    private String apkPath;

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_download);
        ButterKnife.bind(this);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            path = sd.getPath() + File.separator + "yunLong";

            File file = new File(path);
            if (!file.isDirectory()) {
                file.mkdir();
            }
            apkPath = path + File.separator + "qq2018.apk";
        } else {
            Toast.makeText(this, "SD卡不存在", Toast.LENGTH_SHORT).show();
        }

        PermissionUtils.requestMorePermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, 2);

    }

    @OnClick({R.id.bt_download, R.id.bt_stop, R.id.bt_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_download:
                MyIntentService.startUpdateService(this, url, apkPath);
                break;
            case R.id.bt_stop:
                showNotification();
                break;
            case R.id.bt_setting:
                showToAppSettingDialog();
                break;
        }
    }

    private void showToAppSettingDialog() {
        new AlertDialog.Builder(this)
                .setTitle("需要权限")
                .setMessage("我们需要相关权限，才能实现功能，点击前往，将转到应用的设置界面，请开启应用的相关权限。")
                .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.toAppSetting(RetrofitDownloadActivity.this);
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    //系统通知栏8.0适配方案
    private void showNotification() {
        String id = "my_channel_01";
        String name = "我是渠道名字";

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            Toast.makeText(this, mChannel.toString(), Toast.LENGTH_SHORT).show();
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
            }
            notification = new Notification.Builder(this, id)
                    .setChannelId(id)
                    .setContentTitle("开始下载")
                    .setContentText("版本更新中。。。")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setProgress(100, 33, false)
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("开始下载")
                    .setContentText("版本更新中。。。")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true);
            notification = notificationBuilder.build();
        }

        notificationManager.notify(3, notification);
    }

    @OnClick(R.id.bt_clear)
    public void onViewClicked() {

        isClearLogFile(path);
    }

    /**
     * 清除指定文件的个数
     * 示例为 5 代表只需清理前5个文件  文件个数为5以下的这不需要清理
     */
    public void isClearLogFile(String p) {
        List<File> fileList = FileUtils.listFilesInDir(p);
        Logger.e("数组长度----->" + fileList.size());

        if (fileList.size() > 5) {
            for (int i = 0; i < 5; i++) {
                //Logger.e("文件名----->" + fileList.get(i).getName());
                //Logger.e("文件时间----->" + fileList.get(i).lastModified());

                if (FileUtils.isFile(fileList.get(i))) {
                    FileUtils.deleteFile(fileList.get(i));
                }
            }
        } else {
            Toast.makeText(this, "不需要递归删除", Toast.LENGTH_LONG).show();
            return;
        }

        isClearLogFile(p);
    }


}
