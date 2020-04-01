package com.sample.yl.sampledemo.retrofit;

import android.content.Context;

import androidx.annotation.Nullable;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.retrofit.entity.ImgWeal;

import java.util.List;

/**
 * Created by ${jz} on 2019/8/23。
 */
public class GlideImgAdapter extends BaseQuickAdapter<ImgWeal, BaseViewHolder> {
    private Context mContext;

    public GlideImgAdapter(int layoutResId, @Nullable List<ImgWeal> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImgWeal item) {

        Glide.with(mContext)
                .load(item.getUrl())
                .placeholder(R.drawable.loading)
                .into((ImageView) helper.getView(R.id.iv_weal));

    }
}
