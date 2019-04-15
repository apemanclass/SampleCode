package com.sample.yl.sampledemo.gpslocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.yl.sampledemo.R;

import java.util.List;

public class GpsLocActivity extends AppCompatActivity {
    //定位都要通过LocationManager这个类实现
    private LocationManager locationManager;
    private String provider;
    private boolean flag;
    private Location location;

    private Button bt_gps;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_loc);

        bt_gps = (Button) findViewById(R.id.bt_gps);
        tv_text = (TextView) findViewById(R.id.tv_text);

        //获取定位服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            flag = true;
        }

        //获取当前可用的位置控制器
        List<String> list = locationManager.getProviders(true);
        isController(list);

        location = locationManager.getLastKnownLocation(provider);
        bt_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = "";
                if (location != null) {
                    //获取当前位置，这里只用到了经纬度
                    string = "纬度为：" + location.getLatitude() + "，经度为：" + location.getLongitude() + "，数据来源:" + provider;

                } else {
                    if (!provider.equals(LocationManager.GPS_PROVIDER)) {
                        Toast.makeText(GpsLocActivity.this, "请检查GPS是否打开", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GpsLocActivity.this, "正在获取数据中。。。", Toast.LENGTH_SHORT).show();
                    }
                }

                if (flag) {
                    tv_text.setText(string);
                } else {
                    Toast.makeText(GpsLocActivity.this, "no permission", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //绑定定位事件，监听位置是否改变
        //第一个参数为控制器类型
        //第二个参数为监听位置变化的时间间隔（单位：毫秒）
        //第三个参数为位置变化的间隔（单位：米）
        //第四个参数为位置监听器
        locationManager.requestLocationUpdates(provider, 2000, 2, locationListener);
    }

    //是否有可以的位置控制器
    private void isController(List<String> list) {
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            provider = "";
            Toast.makeText(this, "请检查网络或GPS是否打开", Toast.LENGTH_LONG).show();
            return;
        }
    }

    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            flag = grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            // 更新当前经纬度
            location = arg0;
        }
    };

    //关闭时解除监听器
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

}
