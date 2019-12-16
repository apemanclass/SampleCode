package com.sample.yl.sampledemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jz} on 2018/7/20。
 */
public class TestModel {
    private String title;
    private String name;
    private String time;
    private String info;

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info == null ? "" : info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<TestModel> getTestData() {
        List<TestModel> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestModel model = new TestModel();
            model.setTitle("数据条目的标题" + i);
            model.setName("名字:张二娃" + i);
            model.setTime("2018-08-" + i);
            model.setInfo(i + ":真诚是美酒，年份越久越醇香浓型；真诚是焰火，在高处绽放才愈是美丽；真诚是鲜花，送之于人手有余香。");

            data.add(model);
        }
        return data;
    }

    public static List<TestModel> getTestData2() {
        List<TestModel> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestModel model = new TestModel();
            model.setTitle("让人跑不快的沙子" + i);
            model.setName("名字:王麻子" + i);
            model.setTime("2018-08-" + i);
            model.setInfo(i + ":大师说:天下哪有这样的好事，既跑得快，又没有摔伤的危险。都是伴着风险和隐患的啊！");

            data.add(model);
        }
        return data;
    }

    public static List<TestModel> getTestData3() {
        List<TestModel> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestModel model = new TestModel();
            model.setTitle("给他人一个解释的机会" + i);
            model.setName("名字:李四" + i);
            model.setTime("2018-08-" + i);
            model.setInfo(i + ":解释却未必都是找借口，给别人一个解释的机会，不仅可以给他人一个证明的机会，也可以让自己避免损失和失误。");

            data.add(model);
        }
        return data;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
