package com.sample.yl.mylibrary.inject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IdRes;

/**
 * Created by  on 6/18/21。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventType(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener")
public @interface OnClick {
    @IdRes int[] value() default {View.NO_ID};
}
