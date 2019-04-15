package com.sample.yl.sampledemo.rxjava2;

/**
 * Created by ${jz} on 2018/11/3。
 */
public class LoginResponse {
    private String login;


    public String getLogin() {
        return login == null ? "" : login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
