package com.sample.yl.sampledemo.model;

/**
 * Created by  on 6/16/21。
 */
public class UserSerializable implements java.io.Serializable {
    String name;

    public UserSerializable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserSerializable{" +
                "name='" + name + '\'' +
                '}';
    }
}
