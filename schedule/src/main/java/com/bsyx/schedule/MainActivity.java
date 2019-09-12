package com.bsyx.schedule;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Process;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;


public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;
    protected AgentWeb mAgentWeb;
    private LinearLayout container;
    private DrawerLayout dlLeft;
    private ImageView tvIp;
    private AlertDialog.Builder aDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        setContentView(R.layout.activity_main);
        initControls();

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go("http://192.168.0.137:9999/base-web/a");

        initLoadUrl();

        tvIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIpAddsDialog();
            }
        });
    }

    public String getUrl(String ipAdds) {
        return "http://" + ipAdds + "/schapp-web/webPage/unpackage/dist/build/h5";
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        container = (LinearLayout) findViewById(R.id.container);
        dlLeft = (DrawerLayout) findViewById(R.id.drawerLayout);
        tvIp = (ImageView) findViewById(R.id.tv_ip);
    }

    private void initLoadUrl() {
        if (!TextUtils.isEmpty(MyApplication.preferenceUtil.getURL())) {
            String urlIp = MyApplication.preferenceUtil.getURL();
            mAgentWeb.getUrlLoader().loadUrl(getUrl(urlIp));
        } else {
            dlLeft.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
        }
    }

    private void setIpAddsDialog() {
        View view = getLayoutInflater().inflate(R.layout.item_set_ip_dialog, null);
        final EditText etIp = view.findViewById(R.id.et_ip);
        if (!TextUtils.isEmpty(MyApplication.preferenceUtil.getURL())) {
            etIp.setText(MyApplication.preferenceUtil.getURL());
        }

        aDialog = new AlertDialog.Builder(this);
        aDialog.setTitle("设置服务器地址");
        aDialog.setView(view);
        aDialog.setCancelable(false);
        aDialog.setNegativeButton("取 消", null);
        aDialog.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tempIp = etIp.getText().toString();
                if (TextUtils.isEmpty(tempIp)) {
                    Toast.makeText(MainActivity.this, "IP地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyApplication.preferenceUtil.setURL(tempIp);
                showDrawerLayout();
                initLoadUrl();
            }
        });
        aDialog.create().show();
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

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

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

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
