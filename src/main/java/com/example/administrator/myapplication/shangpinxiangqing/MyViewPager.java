package com.example.administrator.myapplication.shangpinxiangqing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class MyViewPager extends ViewPager {


    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        try {
            return super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
