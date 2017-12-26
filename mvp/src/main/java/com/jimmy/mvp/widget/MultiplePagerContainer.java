package com.jimmy.mvp.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;


public class MultiplePagerContainer extends ViewPager {

    private boolean isEnableTouchScroll = true;
    private Field mScroller;

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

    /**
     * 设置viewpager的pager切换时的速度。主要体现在setCurrentItem(index,true)的时候体现出现的速度
     *
     * @param speed 必须大于250毫秒
     */
    public void setScrollSpeed(int speed) {
        SpeedScroller scroller = new SpeedScroller(getContext(), speed);
        try {
            if (mScroller == null) {
                mScroller = ViewPager.class.getDeclaredField("mScroller");
                mScroller.setAccessible(true);
            }
            mScroller.set(this, scroller);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    private static class SpeedScroller extends Scroller {

        private static final Interpolator sINTERPOLATOR = new Interpolator() {
            @Override
            public float getInterpolation(float t) {
                t -= 1.0f;
                return t * t * t * t * t + 1.0f;
            }
        };

        private final int mDuration;

        private static final int MIN_DEFAULT_DURATION = 250;

        SpeedScroller(Context context, int duration) {
            super(context, sINTERPOLATOR);
            duration = Math.max(duration, MIN_DEFAULT_DURATION);
            mDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, Math.max(duration, mDuration));
        }
    }
}
