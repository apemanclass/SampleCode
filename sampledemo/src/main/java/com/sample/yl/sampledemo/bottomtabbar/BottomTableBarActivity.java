package com.sample.yl.sampledemo.bottomtabbar;

import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sample.yl.mylibrary.bottomtabbar.BottomTabBaseActivity;
import com.sample.yl.mylibrary.bottomtabbar.BottomTabView;
import com.sample.yl.sampledemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jz} on 2017/9/12.
 * 测试封装的底部导航框架   微信效果
 */
public class BottomTableBarActivity extends BottomTabBaseActivity {


    @Override
    protected void initCreate() {

    }

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();

        tabItemViews.add(new BottomTabView.TabItemView(this, "首页", R.color.colorDef, R.color.colorSelect, R.drawable.home1, R.drawable.home2));
        tabItemViews.add(new BottomTabView.TabItemView(this, "查看", R.color.colorDef, R.color.colorSelect, R.drawable.mywite1, R.drawable.mywite2));
        tabItemViews.add(new BottomTabView.TabItemView(this, "填报", R.color.colorDef, R.color.colorSelect, R.drawable.add1, R.drawable.add2));
        tabItemViews.add(new BottomTabView.TabItemView(this, "我的", R.color.colorDef, R.color.colorSelect, R.drawable.user1, R.drawable.user2));

        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
        fragments.add(new TabFragment4());

        return fragments;
    }


    /**
     * 实现中间的点击按钮超出底部的布局
     * 只需在布局的根加下面这个属性
     * android:clipChildren="false"
     */
    @Override
    protected View getCenterView() {

        ImageView centerView = new ImageView(this);
        centerView.setImageResource(R.mipmap.ic_launcher_round);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(170, 170);
        layoutParams.leftMargin = 25;
        layoutParams.rightMargin = 25;
        layoutParams.bottomMargin = 10;
        centerView.setLayoutParams(layoutParams);
        centerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomTableBarActivity.this, "点击了centerView事件", Toast.LENGTH_SHORT).show();
            }
        });

        return centerView;
    }
}
