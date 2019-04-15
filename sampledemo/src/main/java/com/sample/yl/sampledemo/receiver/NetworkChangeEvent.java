package com.sample.yl.sampledemo.receiver;

/**
 * Created by ${jz} on 2018/8/8。
 */
public class NetworkChangeEvent {

    public boolean isConnected; //是否存在网络

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
