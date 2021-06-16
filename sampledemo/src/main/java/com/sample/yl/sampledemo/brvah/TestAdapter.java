package com.sample.yl.sampledemo.brvah;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.model.TestModel;

import java.util.List;


/**
 * Created by ${jz} on 2018/7/23。
 */
public class TestAdapter extends BaseQuickAdapter<TestModel, BaseViewHolder> {

    public TestAdapter(int layoutResId, @Nullable List<TestModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestModel item) {

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_info, item.getInfo());
    }
}
