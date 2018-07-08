package com.jimmy.mvp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.jimmy.mvp.widget.MultiplePagerContainer;
import com.jimmy.mvp.adapter.IMultiplePagerDataSource;
import com.jimmy.mvp.adapter.MultiplePagerAdapter;


/**
 * 适用于多个fragment的显示，内部嵌入一个view pager来控制fragment
 *
 * @author yangyoujun
 */

public abstract class AbsActivityContainer<T extends MultiplePagerContainer> extends AbsActivity
        implements IMultiplePagerDataSource, ViewPager.OnPageChangeListener {

    /**
     * 可修改是否拦截touch事件
     */
    protected T mContainer;

    /**
     * 这个adapter可以做成可扩展数量的
     */
    protected MultiplePagerAdapter mAdapter;

    @Override
    protected void onAgree(Bundle savedInstanceState) {
        onCreateActivity(savedInstanceState);
        create();
        onCreated(savedInstanceState);
    }

    private void create() {
        if (onGetPagerCount() <= 0) {
            return;
        }

        mContainer = onCreateViewPager();
        if (mContainer == null) {
            throw new NullPointerException("View Pager is NULL");
        }
        mContainer.addOnPageChangeListener(this);
        mAdapter = onCreateMultiplePagerAdapter();
        if (mAdapter == null) {
            throw new NullPointerException("Adapter is NULL");
        }
        mContainer.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        if (mContainer != null) {
            mContainer.setAdapter(null);
            mContainer.clearOnPageChangeListeners();
            mContainer.setOnPageChangeListener(null);
            mContainer = null;
        }
        if (mAdapter != null) {
            mAdapter.onDestroy();
            mAdapter = null;
        }
        super.onDestroy();
    }

    /**
     * 子类可通过复写此方法，达到修改adapter
     */
    protected MultiplePagerAdapter onCreateMultiplePagerAdapter() {
        return new MultiplePagerAdapter<IMultiplePagerDataSource>(getSupportFragmentManager(), this);
    }

    protected int getCurrentItem() {
        if (mContainer != null) {
            return mContainer.getCurrentItem();
        }
        return -1;
    }

    protected void setCurrentItem(int position) {
        setCurrentItem(position, true);
    }

    protected void setCurrentItem(int position, boolean hasAnimate) {
        if (mContainer != null && mAdapter != null && position > -1 && position < mAdapter.getCount()) {
            mContainer.setCurrentItem(position, hasAnimate);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 获取到权限后，在viewpager创建之前，回调此方法，本方法执行在onCreate()的声明周期中
     */
    protected abstract void onCreateActivity(Bundle savedInstanceState);

    /**
     * 创建一个viewpager，用于当前的容器
     */
    protected abstract T onCreateViewPager();

    /**
     * 创建完viewpager后回调，本方法执行在onCreate()的声明周期中
     */
    protected void onCreated(Bundle savedInstanceState) {
    }

}
