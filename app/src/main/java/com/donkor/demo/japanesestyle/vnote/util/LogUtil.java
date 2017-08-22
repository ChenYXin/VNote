package com.donkor.demo.japanesestyle.vnote.util;

import android.util.Log;

/**
 * 打印日志的工具类
 * Created by Donkor on 2017/8/21.
 */
public class LogUtil {
    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;

    public static void e(String TAG, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.e(TAG + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.e(TAG, msg.substring(start, strLength));
                break;
            }
        }
    }
}
