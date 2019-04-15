package com.sample.yl.sampledemo.retrofitdownload;

/**
 * Created by ${jz} on 2019/3/29。
 */
public class BaseEntity<T> {
    private int code;
    private String text;
    private T target;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }
}
