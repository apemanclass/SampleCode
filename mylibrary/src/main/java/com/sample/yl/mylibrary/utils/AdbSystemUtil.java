package com.sample.yl.mylibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by  on 4/9/21。
 * 通过adb命令控制系统相关配置
 * apk拥有root权限可以直接调用Runtime调用Linux-shell发送ADB命令控制
 * <p>
 * 常见的模拟按键ADB命令
 * reboot 重启
 * input keyevent 26 电源键
 * input keyevent 223 息屏
 * input keyevent 224 亮屏
 * input keyevent 82 菜单锁
 * input keyevent 24 音量+键
 * input keyevent 25 音量-键
 * input keyevent 3 Home键
 * input keyevent 4 返回键
 * input keyevent 5 呼叫键
 * input keyevent 6 挂断键
 * 原文链接：https://blog.csdn.net/black_bird_cn/article/details/79717245
 */
public class AdbSystemUtil {

    /**
     * 判断机器Android是否已经root，即是否获取root权限
     */
    public static boolean checkDeviceRoot() {
        boolean resualt = false;
        int ret = execRootCmdSilent("echo test"); // 通过执行测试命令来检测
        if (ret != -1) {
            Log.i("checkDeviceRoot", "this Device have root!");
            resualt = true;
        } else {
            resualt = false;
            Log.i("checkDeviceRoot", "this Device not root!");
        }
        return resualt;
    }

    /**
     * 执行命令并且输出结果
     */
    public static String execRootCmd(String cmd) {
        String result = "";
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            Process p = Runtime.getRuntime().exec("su");// 经过Root处理的android系统即有su命令
            dos = new DataOutputStream(p.getOutputStream());
            dis = new DataInputStream(p.getInputStream());
            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            String line = null;
            while ((line = dis.readLine()) != null) {
                result += line;
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 执行命令但不关注结果输出
     */
    public static int execRootCmdSilent(String cmd) {
        int result = -1;
        DataOutputStream dos = null;
        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());
            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            p.waitFor();
            result = p.exitValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    //亮屏逻辑代码
    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    public void openScreenOn(Context mContext) {
        if (powerManager == null) {
            powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        }
        if (wakeLock == null) {
            wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG");
        }
        boolean ifOpen = powerManager.isScreenOn();
        if (!ifOpen) {
            //屏幕会持续点亮
            wakeLock.acquire();
            //释放锁，以便2分钟后熄屏。
            wakeLock.release();
        }
    }

    public static void rebootDevice() {
        DataOutputStream os = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("reboot\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setSystemTime(String datetimes) {
        try {
            Process process = Runtime.getRuntime().exec("su");
            String datetime = "";
            datetime = datetimes.toString();
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT\n");
            os.writeBytes("/system/bin/date -s " + datetime + "\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean setTime(Context context, int year, int month, int day, int hour, int minute, int second, int millsecond) {
        if (!isRight(year, month, day, hour, minute, second, millsecond)) {
            // SharedPreferencesUtil.putInt(context,
            // SharedPreferencesKey.SP_KEY_SYSTEMCONTROL_CODE, -2022);
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, --month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millsecond);

        long when = calendar.getTimeInMillis();

        if (when / 1000 < Integer.MAX_VALUE) {
            SystemClock.setCurrentTimeMillis(when);
            return true;
        }

        return false;
    }

    public static void setSysTime(Context context, int year, int month, int day, int hour, int minute, int second) {
        Intent timeIntent = new Intent("android.intent.action.SET_SYSTEM_TIME");
        timeIntent.putExtra("year", year);
        timeIntent.putExtra("month", month);
        timeIntent.putExtra("day", day);//
        timeIntent.putExtra("hour", hour);//
        timeIntent.putExtra("minute", minute);//
        timeIntent.putExtra("second", second);//
        context.sendBroadcast(timeIntent);
    }

    private static boolean isRight(int year, int month, int day, int hour, int minute, int second, int millsecond) {
        int count = 0;

        if (year >= 1970) {
            count++;
            boolean isLeapYear = ((year % 4 == 0 && year % 100 != 0) | (year % 400 == 0)) ? true : false;
            if (isLeapYear) {
                if (month == 2) {
                    if (day >= 1 && day <= 29)
                        count += 2;
                }
            } else {
                if (month == 2) {
                    if (day >= 1 && day <= 28)
                        count += 2;
                }
            }

            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day >= 1 && day <= 31)
                    count += 2;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day >= 1 && day <= 30)
                    count += 2;
            }
        }

        if (hour >= 0 && hour <= 23)
            count++;
        if (minute >= 0 && minute <= 59)
            count++;
        if (second >= 0 && second <= 59)
            count++;

        return count == 6 ? true : false;
    }
}
