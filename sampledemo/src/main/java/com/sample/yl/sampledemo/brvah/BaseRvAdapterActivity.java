package com.sample.yl.sampledemo.brvah;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.sample.yl.sampledemo.BaseActivity;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.TestModel;
import com.sample.yl.mylibrary.utils.ARouterPath;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import butterknife.BindView;
import butterknife.ButterKnife;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ARouterPath.Brvah_BaseRv)
public class BaseRvAdapterActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private TestAdapter adapter;

    //通过Autowired注解 & 将key1作为属性的名称   &  需要在onCreate中调用ARouter.getInstance().inject(this);配合使用
    @Autowired()
    public String key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_rv_adapter);

        ButterKnife.bind(this);

        //region ARouter目标界面的参数接收用法
        ARouter.getInstance().inject(this);
        Logger.t("ARouter").d(key1);

        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        serializationService.init(this);
        TestModel object = serializationService.parseObject(getIntent().getStringExtra("key2"), TestModel.class);
        Logger.t("ARouter序列化").d(object.toString());

        //String model = getIntent().getExtras().getString("key3");
        //Logger.t("ARouter:Bundle").d(model);
        //endregion


        //设置布局
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //设置分割线
        //rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new TestAdapter(R.layout.item_layout, TestModel.getTestData());
        //设置条目的动画效果
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        rv.setAdapter(adapter);

//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
//                rv.setAdapter(adapter);
//                refreshlayout.finishRefresh(2000);
//                //refreshlayout.finishRefresh(false);//传入false表示刷新失败
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishLoadMore(2000);
//                //refreshLayout.finishLoadMore(false);//传入false表示刷新失败
//            }
//        });

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.replaceData(TestModel.getTestData2());
                refreshLayout.finishRefresh(2000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                adapter.addData(TestModel.getTestData3());
                refreshLayout.finishLoadMore(2000);
            }
        });
    }

}
