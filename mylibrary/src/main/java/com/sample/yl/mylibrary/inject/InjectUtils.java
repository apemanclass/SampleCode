package com.sample.yl.mylibrary.inject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by  on 6/15/21。
 * 自定义注解工具
 */
public class InjectUtils {

    /**
     * 注解加反射的机制实现自动设置activity的布局ID
     */
    public static void injectContent(Object object) {
        Class<?> activityClass = object.getClass();
        ContentView mContentView = activityClass.getAnnotation(ContentView.class);
        if (null == mContentView) {
            Logger.t("InjectUtils = ").e("ContentView is null");
            return;
        }
        int layoutID = mContentView.value();
        try {
            Method setContentViewMethod = activityClass.getMethod("setContentView", int.class);
            setContentViewMethod.invoke(object, layoutID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                        field.set(activity, obj);//该字段如果是static修饰的，反射第一参数可以传null
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 注解加反射的机制实现自动设置view的点击事件
     */
    public static void injectEvent(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Method[] declaredMethods = cls.getDeclaredMethods();

        for (Method method : declaredMethods) {
            //获取方法上的所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    // OnClickListener.class
                    Class listenerType = eventType.listenerType();
                    // setOnClickListener
                    String listenerSetter = eventType.listenerSetter();

                    try {
                        // 不需要关心到底是 OnClick 还是 OnLongClick
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        //调用当前类型注解的方法  因为知道当前定义的方法是int的数组  直接强转
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);

                        method.setAccessible(true);
                        ListenerInvocationHandler<Activity> handler = new ListenerInvocationHandler<>(activity, method);
                        Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, handler);
                        // 遍历注解的值
                        for (int viewId : viewIds) {
                            // 获得当前activity的view（赋值）
                            View view = activity.findViewById(viewId);
                            // 获取指定的方法(不需要判断是Click还是LongClick)
                            // 如获得：setOnClickListener方法，参数为OnClickListener
                            // 获得 setOnLongClickListener，则参数为OnLongClickListener
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            // 执行方法
                            setter.invoke(view, listenerProxy);//执行setOnclickListener里面的回调 onclick方法
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 还可能在自定义view注入，所以是泛型： T = Activity/View
     */
    static class ListenerInvocationHandler<T> implements InvocationHandler {
        private Method method;
        private T target;

        public ListenerInvocationHandler(T target, Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }
}
