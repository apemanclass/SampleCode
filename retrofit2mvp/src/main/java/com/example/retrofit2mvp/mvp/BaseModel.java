package com.example.retrofit2mvp.mvp;

/**
 * Created by ${jz} on 2018/10/22。
 * mode基类
 */
public class BaseModel<T> {
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

