package com.sample.yl.sampledemo.timepicker;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.sample.yl.sampledemo.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jz on 2017/7/24.
 * 多种选择时间的的示例代码
 */
public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {
    private Button bt_all, bt_2, bt_3, bt_4, bt_5;
    private TextView tv;
    private TimePickerDialog mDialogAll, mDialogYearMonth, mDialogYearMonthDay, mDialogMonthDayHourMinute, mDialogHourMinute;
    private Context mContext;
    private TextClock textClock;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        mContext = this;
        initControls();
        regListener();

        timeDateAll();
        dialogHourMinute();
        dialogMonthDayHourMinute();
        dialogYearMonth();
        dialogYearMonthDay();

        try {
            timerDemo();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        bt_all = (Button) findViewById(R.id.bt_all);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        tv = (TextView) findViewById(R.id.tv);
        textClock = findViewById(R.id.tc);
    }

    /**
     * 注册组件的监听
     */
    private void regListener() {
        bt_all.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_all:
                mDialogAll.show(getSupportFragmentManager(), "all");
                break;
            case R.id.bt_2:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
                break;
            case R.id.bt_3:
                mDialogYearMonth.show(getSupportFragmentManager(), "year_month");
                break;
            case R.id.bt_4:
                mDialogMonthDayHourMinute.show(getSupportFragmentManager(), "month_day_hour_minute");
                break;
            case R.id.bt_5:
                mDialogHourMinute.show(getSupportFragmentManager(), "hour_minute");
                break;
        }
    }

    /**
     * 日期及时间选择
     */
    private void timeDateAll() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();

//        mDialogAll = new TimePickerDialog.Builder()
//                .setMinMillseconds(System.currentTimeMillis())
//                .setThemeColor(R.color.colorPrimary)
//                .setWheelItemTextSize(16)
//                .setCallBack(this)
//                .build();
    }

    /**
     * 年月选择
     */
    private void dialogYearMonth() {
        mDialogYearMonth = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setCallBack(this)
                .build();
    }

    /**
     * 年月日选择
     */
    private void dialogYearMonthDay() {
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
    }

    /**
     * 月日时分选择
     */
    private void dialogMonthDayHourMinute() {
        mDialogMonthDayHourMinute = new TimePickerDialog.Builder()
                .setType(Type.MONTH_DAY_HOUR_MIN)
                .setCallBack(this)
                .build();
    }

    /**
     * 时分选择
     */
    private void dialogHourMinute() {
        mDialogHourMinute = new TimePickerDialog.Builder()
                .setType(Type.HOURS_MINS)
                .setCallBack(this)
                .build();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = getDateToString(millseconds);
        tv.setText(text);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    private void timerDemo() throws ParseException {
        String strTimer = "2018-12-11 09:40:25";
        long timeTemp = sf.parse(strTimer).getTime();

        Date date = new Date(timeTemp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        Log.e("TAG", year + "--" + month + "--" + day + "--" + week + "--" + hour + "--" + minute + "--" + second + "");
    }
}
