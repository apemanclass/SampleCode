package com.sample.yl.mylibrary.customhead;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sample.yl.mylibrary.R;

/**
 * 对统一的布局title页面及一些事件进行封装
 */
public class TitleLayout extends LinearLayout {
    private ImageView titleBack, titleMore;
    private TextView title;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.include_head, this);

        titleBack = (ImageView) findViewById(R.id.title_back);
        this.backClick();
        title = (TextView) findViewById(R.id.title_text);
        titleMore = (ImageView) findViewById(R.id.title_more);
    }

    /**
     * 该控件在每个页面的处理逻辑都是一样的,可以直接在自定义控件中这样封装
     */
    public void backClick() {
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    /**
     * 设置标题的back键是否可见   默认显示
     */
    public void setBackVisible(int visibility) {
        titleBack.setVisibility(visibility);
    }

    /**
     * 设置标题的文字
     */
    public void setTitle(String str) {
        title.setText(str);
    }

    /**
     * 该控件的处理业务逻辑在每个页面都不一样，就需要把该控件的事件提供给外部activity自行处理逻辑
     *
     * @param listener 事件回调
     */
    public void setMoreClick(OnClickListener listener) {
        titleMore.setOnClickListener(listener);
    }

    /**
     * 设置标题的more键是否可见    默认显示
     */
    public void setMoreVisible(int visibility) {
        titleMore.setVisibility(visibility);
    }
}
