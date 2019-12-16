package com.sample.yl.sampledemo.arouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${jz} on 2018/8/8。
 * 用于帮助 Android App 进行组件化改造的框架 —— 支持模块间的路由、通信、解耦
 */
public class ARouterActivity extends AppCompatActivity {

    @BindView(R.id.bt_router)
    Button btRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_router)
    public void onViewClicked() {
        /**
         * 直接路由跳转的写法
         */
        //ARouter.getInstance().build("/brvah/BaseRvAdapterActivity").navigation();

        /**
         * 携带参数的路由跳转写法---SerializationService
         */
        TestModel model = TestModel.getTestData2().get(1);


        ARouter.getInstance().build("/brvah/BaseRvAdapterActivity")
                .withString("key1", "ARouter跳转数据")
                .withObject("key2", model)
                .navigation();

        /**
         * 携带参数的路由跳转写法---Bundle
         */
        Bundle bundle = new Bundle();
        bundle.putString("key3", model.toString());

//        ARouter.getInstance().build("/brvah/BaseRvAdapterActivity")
//                .with(bundle)
//                .navigation();
    }
}
