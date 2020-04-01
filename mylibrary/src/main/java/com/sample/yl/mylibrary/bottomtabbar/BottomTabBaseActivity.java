package com.sample.yl.mylibrary.bottomtabbar;

import android.os.Bundle;
import android.view.View;

import com.sample.yl.mylibrary.R;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


/**
 * Created by jz on 2017/7/24.
 * 实现底部导航栏的抽象类
 */
public abstract class BottomTabBaseActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomTabView bottomTabView;
    FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_tab);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };

        viewPager.setAdapter(adapter);

        if (getCenterView() == null) {
            bottomTabView.setTabItemViews(getTabViews());
        } else {
            bottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }

        bottomTabView.setUpWithViewPager(viewPager);

        initCreate();
    }


    /**
     * 初始化入口的一些
     */
    protected abstract void initCreate();

    /**
     * 可变的导航栏的item个数的抽象方法
     */
    protected abstract List<BottomTabView.TabItemView> getTabViews();


    /**
     * 可变的Fragment碎片个数的抽象方法
     */
    protected abstract List<Fragment> getFragments();


    /**
     * 中间导航栏的布局效果，可自定义
     */
    protected View getCenterView() {
        return null;
    }

}
