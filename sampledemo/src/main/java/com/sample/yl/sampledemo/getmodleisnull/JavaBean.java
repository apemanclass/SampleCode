package com.sample.yl.sampledemo.getmodleisnull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jz} on 2018/4/23。
 */
public class JavaBean {
    private String str;

    private List<String> mList;

    private String name;

    private String age;

    private String love;

    /**
     * 如果String类型的字段为空，那么返回""，
     * 外部在使用getStr().equal()等之类方法时如果忘记进行null判断，
     * 也不会造成空指针异常
     */
    public String getStr() {
        return str == null ? "" : str;
    }

    //没有加判断为null的情况，会造成空指针异常
    public String getStrNo() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    /**
     * 如果List类型字段为空，那么返回空列表。
     * 外部在使用getList().get(i)或者getList().size()等时如果忘记进行null判断，
     * 也不会造成空指针异常
     */
    public List<String> getList() {
        if (mList == null) {
            return new ArrayList<>();
        }
        return mList;
    }


    public String getName() {
        return name == null ? "-.-" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age == null ? "" : age;
    }

    public String getLove() {
        return love == null ? "" : love;
    }
}
