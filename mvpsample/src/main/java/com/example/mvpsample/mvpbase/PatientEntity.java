package com.example.mvpsample.mvpbase;

/**
 * Created by ${jz} on 2018/11/6。
 */
public class PatientEntity {

    private String name;
    private String intime;
    private String bpp_id;
    private String bpp_code;
    private String days;
    private String bedNu;
    private String roomNu;
    private String yblx;
    private String sblx;
    private String bqzk;// 病情（病危，病种。。）
    private String admissionNu;
    private String nursingLev;
    private String gender;
    private String national;
    private String age;
    private Integer status;
    private String cost;//余额
    private String dangerNum;//危急值
    private String surgerytime; //手术时间
    private String surgeryname; //手术名称
    private String doctor;
    private String nurse;

    public String getName() {
        return name == null ? "" : name;
    }

    public String getIntime() {
        return intime == null ? "" : intime;
    }

    public String getBpp_id() {
        return bpp_id == null ? "" : bpp_id;
    }

    public String getBpp_code() {
        return bpp_code == null ? "" : bpp_code;
    }

    public String getDays() {
        return days == null ? "" : days;
    }

    public String getBedNu() {
        return bedNu == null ? "" : bedNu;
    }

    public String getRoomNu() {
        return roomNu == null ? "" : roomNu;
    }

    public String getYblx() {
        return yblx == null ? "" : yblx;
    }

    public String getSblx() {
        return sblx == null ? "" : sblx;
    }

    public String getBqzk() {
        return bqzk == null ? "" : bqzk;
    }

    public String getAdmissionNu() {
        return admissionNu == null ? "" : admissionNu;
    }

    public String getNursingLev() {
        return nursingLev == null ? "" : nursingLev;
    }

    public String getGender() {
        return gender == null ? "" : gender;
    }

    public String getNational() {
        return national == null ? "" : national;
    }

    public String getAge() {
        return age == null ? "" : age;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCost() {
        return cost == null ? "" : cost;
    }

    public String getDangerNum() {
        return dangerNum == null ? "" : dangerNum;
    }

    public String getSurgerytime() {
        return surgerytime == null ? "" : surgerytime;
    }

    public String getSurgeryname() {
        return surgeryname == null ? "" : surgeryname;
    }

    public String getDoctor() {
        return doctor == null ? "" : doctor;
    }

    public String getNurse() {
        return nurse == null ? "" : nurse;
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "name='" + name + '\'' +
                ", intime='" + intime + '\'' +
                ", bpp_id='" + bpp_id + '\'' +
                ", bpp_code='" + bpp_code + '\'' +
                ", days='" + days + '\'' +
                ", bedNu='" + bedNu + '\'' +
                ", roomNu='" + roomNu + '\'' +
                ", yblx='" + yblx + '\'' +
                ", sblx='" + sblx + '\'' +
                ", bqzk='" + bqzk + '\'' +
                ", admissionNu='" + admissionNu + '\'' +
                ", nursingLev='" + nursingLev + '\'' +
                ", gender='" + gender + '\'' +
                ", national='" + national + '\'' +
                ", age='" + age + '\'' +
                ", status=" + status +
                ", cost='" + cost + '\'' +
                ", dangerNum='" + dangerNum + '\'' +
                ", surgerytime='" + surgerytime + '\'' +
                ", surgeryname='" + surgeryname + '\'' +
                ", doctor='" + doctor + '\'' +
                ", nurse='" + nurse + '\'' +
                '}';
    }
}
