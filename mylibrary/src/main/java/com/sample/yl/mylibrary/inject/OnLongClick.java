package com.sample.yl.mylibrary.inject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IdRes;

/**
 * Created by {jz} on 6/21/21。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventType(listenerType = View.OnLongClickListener.class, listenerSetter = "setOnLongClickListener")
public @interface OnLongClick {
    @IdRes int[] value() default {View.NO_ID};
}