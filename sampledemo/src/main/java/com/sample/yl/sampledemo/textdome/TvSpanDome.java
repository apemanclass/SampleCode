package com.sample.yl.sampledemo.textdome;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.yl.sampledemo.BuildConfig;
import com.sample.yl.sampledemo.R;

public class TvSpanDome extends AppCompatActivity {
    private TextView tv1, tv2;
    private static Context context;

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_span_dome);
        context = this;

        initControls();

        tv1.setText(Html.fromHtml(getString(R.string.different_color_text), null, null));


        String html = getString(R.string.from_html_text);
        // 让链接可点击
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        // ResourceImageGetter用来处理TextView中的图片
        tv2.setText(Html.fromHtml(html, new ResourceImageGetter(), null));
    }

    private static class ResourceImageGetter implements Html.ImageGetter {
        // Constructor takes a Context
        public Drawable getDrawable(String source) {
            int path = context.getResources().getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID);
            Drawable drawable = context.getResources().getDrawable(path);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            Toast.makeText(context, "back", Toast.LENGTH_SHORT).show();
            return false;
        } else if (KeyEvent.KEYCODE_HOME == keyCode) {
            Toast.makeText(context, "home", Toast.LENGTH_SHORT).show();
            return false;
        } else if (KeyEvent.KEYCODE_MENU == keyCode) {
            Toast.makeText(context, "menu", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
