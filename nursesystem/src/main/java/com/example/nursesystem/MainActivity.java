package com.example.nursesystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout dlLeft;
    private XWalkView webView;
    private ImageView tvIp;

    private AlertDialog.Builder dialog;


    //private String url = "http://192.168.0.19:1111/qc";
    private String url = "http://";

    class MyUIClient extends XWalkUIClient {
        MyUIClient(XWalkView view) {
            super(view);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();

        initWebView();

        postUrl();

        tvIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIpDialog();
            }
        });
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        tvIp = (ImageView) findViewById(R.id.tv_ip);
        dlLeft = (DrawerLayout) findViewById(R.id.drawerLayout);
        webView = (XWalkView) findViewById(R.id.wb);
    }

    private void initWebView() {
        XWalkSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//启用js
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//js和android交互
        settings.setAllowFileAccess(true); // 允许访问文件
//        settings.setAppCacheEnabled(false); //设置H5的缓存打开,默认关闭
        settings.setUseWideViewPort(true);//设置webview自适应屏幕大小
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.setLoadWithOverviewMode(false);//设置webview自适应屏幕大小
        settings.setDomStorageEnabled(true);//设置可以使用localStorage
        settings.setSupportZoom(false);//关闭zoom按钮
        settings.setBuiltInZoomControls(false);//关闭Zoom
//        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级
        //不使用缓存:
        settings.setCacheMode(XWalkSettings.LOAD_NO_CACHE);

        webView.setDrawingCacheEnabled(false);
        webView.setUIClient(new MyUIClient(webView));
    }

    /**
     * 显示隐藏侧滑菜单
     */
    private void showDrawerLayout() {
        if (!dlLeft.isDrawerOpen(Gravity.LEFT)) {
            dlLeft.openDrawer(Gravity.LEFT);
        } else {
            dlLeft.closeDrawer(Gravity.LEFT);
        }
    }

    private void postUrl() {
        if (!TextUtils.isEmpty(MyApplication.preferenceUtil.getURL())) {
            String urlIp = MyApplication.preferenceUtil.getURL();
            webView.loadUrl(url + urlIp);
        } else {
            dlLeft.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
        }
    }

    private void setIpDialog() {
        RelativeLayout inflate = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_set_ip_dialog, null);
        final EditText etIp = inflate.findViewById(R.id.et_ip);
        if (!TextUtils.isEmpty(MyApplication.preferenceUtil.getURL())) {
            etIp.setText(MyApplication.preferenceUtil.getURL());
        }

        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("设置服务器地址");
        dialog.setView(inflate);
        dialog.setCancelable(false);
        dialog.setNegativeButton("取 消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("确 定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String temp = etIp.getText().toString();
                MyApplication.preferenceUtil.setURL(temp);
                showDrawerLayout();
                postUrl();
            }
        });
        dialog.create().show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webView.getNavigationHistory().canGoBack()) {
                webView.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (webView != null) {
            webView.onNewIntent(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.clearCache(true);//清空网页访问留下的缓存数据。
            webView.clearSslPreferences();//清除ssl信息
            //webView.clearFormData();//清除form表单数据
            webView.clearMatches();// 清除网页查找的高亮匹配字符。
            //webView.clearCacheForSingleFile("");//清除单个文件缓存
            webView.onDestroy();
        }
    }
}
