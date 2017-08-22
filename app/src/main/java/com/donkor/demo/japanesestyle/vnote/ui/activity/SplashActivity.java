package com.donkor.demo.japanesestyle.vnote.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.donkor.demo.japanesestyle.vnote.R;
import com.donkor.demo.japanesestyle.vnote.util.LogUtil;
import com.donkor.demo.japanesestyle.vnote.util.SPrefUtil;

/**
 * 功能：使用ViewPager实现初次进入应用时的引导页
 * (1)判断是否是首次加载应用--采取读取SharedPreferences的方法
 * (2)是，则进入引导activity；否，则进入MainActivity
 * (3)1s后执行(2)操作
 * Created by Donkor on 2017/8/21.
 */

public class SplashActivity extends Activity {

    private SPrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefUtil = SPrefUtil.getInstance(this);

        if (prefUtil.isFirstLogin()) {
            mHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY, 1000);
        } else {
            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY, 1000);
        }
    }

    // *************************************************
    // Handler:跳转至不同页面
    // *************************************************
    private final static int SWITCH_MAINACTIVITY = 1000;//跳转到主界面
    private final static int SWITCH_GUIDACTIVITY = 1001;//跳转到引导页面
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent mIntent = new Intent();
            switch (msg.what) {
                case SWITCH_MAINACTIVITY:
                    mIntent.setClass(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mIntent);
                    SplashActivity.this.finish();
                    break;
                case SWITCH_GUIDACTIVITY:
                    mIntent = new Intent();
                    mIntent.setClass(SplashActivity.this, GuideActivity.class);
                    SplashActivity.this.startActivity(mIntent);
                    SplashActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
