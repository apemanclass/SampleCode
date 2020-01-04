package com.sample.yl.sampledemo.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.orhanobut.logger.Logger;
import com.sample.yl.sampledemo.R;
import com.sample.yl.sampledemo.retrofit.entity.ImgWeal;

import java.util.List;

/**
 * Created by ${jz} on 2020/1/3。
 * 切换图片翻页适配器
 */
public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ImgWeal> listModel;
    private LayoutInflater inflater;

    public MyPagerAdapter(Context context, List<ImgWeal> list) {
        this.mContext = context;
        this.listModel = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listModel.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.activity_photo_view_pager, null);
        PhotoView photoView = view.findViewById(R.id.photo_view);
        Glide.with(mContext)
                .load(listModel.get(position).getUrl())
                .placeholder(R.drawable.loading)
                .into(photoView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
