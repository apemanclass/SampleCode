package com.sample.yl.sampledemo.ajavademo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jz} on 2018/12/13。
 */
public class ArrayListDemo {

    public static void main(String args[]) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("5");
//        list2.add("6");
//        list2.add("7");

        ArrayList<String> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);

        List<javaBean> list3 = new ArrayList<>();
        javaBean bean = new javaBean();
        bean.patientName = "张三";
        bean.phoneNu = bean.phoneNu + 1;
        bean.phoneNu = bean.phoneNu + 1;
//        list3.add(bean);
//        list3.add(bean);
        System.out.println("长度=" + list3.size());


        String[] strings = new String[]{"1", "1", "1", "1", "1", "1", "1"};
        //System.out.println("长度=" + strings.length);

        String sss = "5222床";
        //System.out.println("长度=" + sss.length() + "截取后=" + sss.substring(0, sss.length() - 1));
        for (javaBean temp : list3) {
            System.out.println(temp.toString());
        }
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i).toString());
        }


        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(4);
        integers.add(5);
        System.out.println("结果=" + integers);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) % 2 == 0) {
                integers.remove(i);
                i = 0;
            }
        }

        System.out.println("结果=" + integers);
    }
}
