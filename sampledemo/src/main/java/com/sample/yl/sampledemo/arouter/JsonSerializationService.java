package com.sample.yl.sampledemo.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by ${jz} on 2019/12/16。
 * 处理传递参数中自定义的Object---》withObject
 */
@Route(path = "/custom/json")
public class JsonSerializationService implements SerializationService {
    Gson gson;

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return gson.fromJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return gson.fromJson(input, clazz);
    }

    @Override
    public void init(Context context) {
        gson = new Gson();
    }
}
