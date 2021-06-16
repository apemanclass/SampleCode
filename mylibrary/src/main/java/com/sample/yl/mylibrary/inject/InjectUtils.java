package com.sample.yl.mylibrary.inject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by  on 6/15/21。
 * 自定义注解工具
 */
public class InjectUtils {

    /**
     * 注解加反射的机制实现自动findViewById
     */
    public static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();

        //获得此类所以的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            //判断属性是否被该注解声明
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                //获取注解中设置的值ID
                int id = injectView.value();
                View view = activity.findViewById(id);

                //设置访问权限，允许操作private的属性
                field.setAccessible(true);
                try {
                    //反射赋值
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注解加反射的机制实现自动获取页面跳转数据绑定
     */
    public static void injectAutoWired(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        //获得数据
        Bundle extras = activity.getIntent().getExtras();
        if (extras == null) {
            return;
        }

        //获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(AutoWired.class)) {
                AutoWired autoWired = field.getAnnotation(AutoWired.class);
                //获得key
                String key = TextUtils.isEmpty(autoWired.value()) ? field.getName() : autoWired.value();

                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);

                    /**
                     * Parcelable数组类型不能直接设置，其他的都可以。
                     */
                    //获得数组单个元素类型
                    Class<?> componentType = field.getType().getComponentType();
                    //当前属性是数组并且是 Parcelable（子类）数组
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                        //创建对应类型的数组并由objs拷贝
                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }

                    field.setAccessible(true);
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
