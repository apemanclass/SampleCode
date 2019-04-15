package com.sample.yl.sampledemo.ajavademo;

/**
 * Created by ${jz} on 2019/1/4。
 */
public class javaBean {
    public String deptName = "";    //区域名称

    public String bedNu = "";       //床位信息

    public String patientName = ""; //病人姓名

    public String callCode = "";    //托管科室的设备编号

    public int phoneNu = 0;    //每条未接电话的次数

    @Override
    public String toString() {
        return "javaBean{" +
                "deptName='" + deptName + '\'' +
                ", bedNu='" + bedNu + '\'' +
                ", patientName='" + patientName + '\'' +
                ", callCode='" + callCode + '\'' +
                ", phoneNu=" + phoneNu +
                '}';
    }
}
