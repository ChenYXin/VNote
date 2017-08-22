package com.donkor.demo.japanesestyle.vnote.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.donkor.demo.japanesestyle.vnote.R;
import com.donkor.demo.japanesestyle.vnote.util.SPrefUtil;

import java.util.ArrayList;

/**
 *
 * Called when the activity is first created.
 * Created by Donkor on 2017/8/21.
 */

public class GuideActivity extends Activity {
    private ViewPager viewPager;

    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";

    /**
     * 装分页显示的view的数组
     */
    private ArrayList<View> pageViews;
    private ImageView imageView;

    /**
     * 将小圆点的图片用数组表示
     */
    private ImageView[] imageViews;

    // 包裹滑动图片的LinearLayout
    private ViewGroup viewPics;

    // 包裹小圆点的LinearLayout
    private ViewGroup viewPoints;

    private SPrefUtil prefUtil;

    /**
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 将要分页显示的View装入数组中
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.viewpager_page1, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_page2, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_page3, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_page4, null));

        prefUtil = new SPrefUtil(GuideActivity.this);


        // 创建imageviews数组，大小是要显示的图片的数量
        imageViews = new ImageView[pageViews.size()];
        // 从指定的XML文件加载视图
        viewPics = (ViewGroup) inflater.inflate(R.layout.activity_guide, null);

        // 实例化小圆点的linearLayout和viewpager
        viewPoints = (ViewGroup) viewPics.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) viewPics.findViewById(R.id.guidePages);

        // 添加小圆点的图片
        for (int i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(GuideActivity.this);
            // 设置小圆点imageview的参数
            //创建一个宽高均为20的布局
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
            lp.setMargins(15, 5, 15, 250);
            imageView.setLayoutParams(lp);
            // 将小圆点layout添加到数组中
            imageViews[i] = imageView;

            // 默认选中的是第一张图片，此时第一个小圆点是选中状态，其他不是
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.mipmap.dot);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.white_dot);
            }

            // 将imageviews添加到小圆点视图组
            viewPoints.addView(imageViews[i]);
        }

        // 显示滑动图片的视图
        setContentView(viewPics);

        // 设置viewpager的适配器和监听事件
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) new GuidePageChangeListener());
    }

    class GuidePageAdapter extends PagerAdapter implements View.OnClickListener {

        // 销毁position位置的界面
        @Override
        public void destroyItem(View v, int position, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) v).removeView(pageViews.get(position));

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        // 获取当前窗体界面数
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return pageViews.size();
        }

        // 初始化position位置的界面
        @Override
        public Object instantiateItem(View v, int position) {
            // TODO Auto-generated method stub
            ((ViewPager) v).addView(pageViews.get(position));

            // 测试页卡3内的按钮事件
            if (position == 3) {
                Button btnCloseGuide = (Button) v.findViewById(R.id.btn_Close_Guide);
                btnCloseGuide.setOnClickListener(this);
            }

            return pageViews.get(position);
        }

        /**
         * 跳转到主界面
         */
        public void mainAct() {
            Intent mIntent = new Intent();
            mIntent.setClass(GuideActivity.this, MainActivity.class);
            prefUtil.setValue("guide", "ClassFragment");
            GuideActivity.this.startActivity(mIntent);
            GuideActivity.this.finish();
        }

        // 判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View v, Object arg1) {
            // TODO Auto-generated method stub
            return v == arg1;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_Close_Guide:
                    mainAct();
                    break;
            }
        }
    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[position]
                        .setBackgroundResource(R.mipmap.dot);
                // 不是当前选中的page，其小圆点设置为未选中的状态
                if (position != i) {
                    imageViews[i].setBackgroundResource(R.mipmap.white_dot);
                }
            }
        }
    }
}