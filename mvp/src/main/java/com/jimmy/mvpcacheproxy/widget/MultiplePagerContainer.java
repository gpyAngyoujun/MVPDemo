package com.jimmy.mvpcacheproxy.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MultiplePagerContainer extends ViewPager {

    private boolean isEnableTouchScroll = true;

    public MultiplePagerContainer(Context context) {
        super(context);
    }

    public MultiplePagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isEnableTouchScroll ? super.onInterceptTouchEvent(ev) : isEnableTouchScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isEnableTouchScroll ? super.onTouchEvent(ev) : isEnableTouchScroll;
    }

    public void enableTouchScroll(boolean isEnable) {
        isEnableTouchScroll = isEnable;
    }
}
