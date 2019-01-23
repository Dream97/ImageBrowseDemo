package com.rastargame.rick.rickivbrowse.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author: Rick
 * Email: zhiyuanfeng@rastar.com
 * Date: 2019/1/23
 */
public class RickViewPager extends ViewPager {


    public RickViewPager(@NonNull Context context) {
        super(context, null);
    }

    public RickViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**
         * 防止触控越界
         */
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}
