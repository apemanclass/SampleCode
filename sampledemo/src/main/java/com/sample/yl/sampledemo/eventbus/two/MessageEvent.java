package com.sample.yl.sampledemo.eventbus.two;

/**
 * Created by ${jz} on 2018/4/25。
 * 消息事件类
 */
public class MessageEvent {

    private String msg;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
