package com.sample.yl.sampledemo.retrofitdownload;

/**
 * Created by ${jz} on 2018/8/30。
 */
public class RxEvent {
    private int code;
    private Object object;

    public RxEvent(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public RxEvent() {
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
