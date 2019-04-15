package com.sample.yl.sampledemo.immersionbar;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.sample.yl.sampledemo.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 导航栏结合侧滑返回使用
 */
public class BackActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        ImmersionBar.with(this).titleBar(R.id.toolbar).navigationBarColor(R.color.colorAccent).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ImmersionBar.with(this).destroy();
    }
}
