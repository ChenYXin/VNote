package com.donkor.demo.japanesestyle.vnote.util;

/**
 * 所有的SharePreference中定义的变量,都在这里集中！！
 * Created by Donkor on 2017/8/21.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class SPrefUtil {
    // =====================================定义常量=======================================
    public static final String TAG = "PrefsUtil";

    private static SPrefUtil instance; // 当前实体类
    public static final String SP_NAME = "vfuchong";
    public static Context context;

    // =====================================定义变量=======================================
    private static SharedPreferences sprefer = null;
    private static SharedPreferences.Editor spEdit = null;

    /**
     * 构造函数
     */
    public SPrefUtil(Context context) {
        SPrefUtil.context = context;
        sprefer = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        spEdit = sprefer.edit();
    }

    // ===================================自定义函数===================================

    /**
     * 得到当前实体类,单例模式
     */
    public static SPrefUtil getInstance(Context context) {
        if (instance == null) {
            instance = new SPrefUtil(context);
        }
        return instance;
    }


    /**
     * 判断是否是第一次登陆
     *
     * @return
     */
    public boolean isFirstLogin() {
        PackageManager manager;
        PackageInfo info = null;
        boolean isFirstLogin = false;
        String storeVersion = getValue("store_app_version", "null");

        manager = context.getPackageManager();

        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if ((storeVersion.equals("null")) || !storeVersion.equals("" + info.versionCode)) {
            isFirstLogin = true;
            //更新保存的版本号
            setValue("store_app_version", "" + info.versionCode);
        } else {
            isFirstLogin = false;
        }
        return isFirstLogin;
    }


    // ===================================Other======================================
    // ===================================Other======================================
    // ===================================Other======================================

    /**
     * 获得键值对
     */
    public String getValue(String key, String def_value) {
        if (sprefer != null) {
            return sprefer.getString(key, def_value);
        }
        return def_value;
    }

    /**
     * 设置键值対
     */
    public void setValue(String key, String value) {
        if (spEdit != null) {
            spEdit.putString(key, value);
            spEdit.commit();
        }
    }

    /**
     * 删除指定键数据
     */
    public void deleteValue(String key) {
        if (spEdit != null) {
            spEdit.remove(key);
            spEdit.commit();
        }
    }
}
