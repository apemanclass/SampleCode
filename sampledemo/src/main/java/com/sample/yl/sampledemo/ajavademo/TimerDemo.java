package com.sample.yl.sampledemo.ajavademo;

import android.util.ArrayMap;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ${jz} on 2018/12/11。
 */
public class TimerDemo {


    public static void main(String args[]) throws ParseException {
        boolean isFlag = true;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String strnum = "8,12,17,24";

        String[] timerNum = strnum.split(",");

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(timerNum));

        System.out.println("strnum:" + timerNum.length);

        String strTimer = "2018-12-11 17:40:25";
        long timeTemp = sf.parse(strTimer).getTime();

        Date date = new Date(timeTemp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println(year + "--" + month + "--" + day + "--" + week + "--" + hour + "--" + minute + "--" + second);

        for (int i = 0; i < list.size(); i++) {
            if (hour.equals(list.get(i))) {
                System.out.println("当前" + list.get(i));
                list.remove(i);
                if (isFlag) {
                    System.out.println("执行从启");
                    isFlag = false;
                }
            } else {
                isFlag = true;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (String item : list) {
            //System.out.println("item:" + item);
            builder.append(item).append(",");
        }


        System.out.println("转换后:" + builder.toString().substring(0, builder.length() - 1));
    }
}
