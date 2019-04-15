package com.sample.yl.sampledemo.ajavademo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${jz} on 2019/1/7。
 */
public class MapDemo {

    public static void main(String args[]) {
        Map<String, javaBean> callLogMap = new HashMap<>();
        javaBean bean = new javaBean();
        bean.deptName = "呼吸内科";
        bean.phoneNu = 1;
        callLogMap.put("55", bean);

        javaBean javaBean = callLogMap.get("55");
        javaBean.phoneNu = javaBean.phoneNu + 1;
        //callLogMap.put("55", javaBean);


        System.out.println(javaBean.toString());


        String strnum = "8,12,17,24";
        String[] timerNum = strnum.split(",");

        for (String item : timerNum) {
            System.out.println("item:" + item);
        }
    }
}
