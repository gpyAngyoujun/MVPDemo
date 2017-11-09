package com.jimmy.mvp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.jimmy.mvp.widget.MultiplePagerContainer;
import com.jimmy.mvp.adapter.IMultiplePagerDataSource;
import com.jimmy.mvp.adapter.MultiplePagerAdapter;


/**
 * Created by yangyoujun on 17-8-26.
 * 适用于多个fragment的显示，内部嵌入一个view pager来控制fragment
 */

public abstract class AbsActivityContainer<T extends MultiplePagerContainer> extends AbsActivity
        implements IMultiplePagerDataSource, ViewPager.OnPageChangeListener {

    /* 可修改是否拦截touch事件 */
    protected T mContainer;
    /* 这个adapter可以做成可扩展数量的 */
    protected MultiplePagerAdapter mAdapter;

    @Override
    protected final void onAgree(Bundle savedInstanceState) {
        onCreateActivity(savedInstanceState);
        create();
    }

    private void create() {
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
            mContainer.removeOnPageChangeListener(this);
            mContainer = null;
        }
        if (mAdapter != null) {
            mAdapter.onDestroy();
            mAdapter = null;
        }
        super.onDestroy();
    }

    /* 子类可通过复写此方法，达到修改adapter */
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
        if (mContainer != null && mAdapter != null && position > -1 && position < mAdapter.getCount()) {
            mContainer.setCurrentItem(position);
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

    protected abstract void onCreateActivity(Bundle savedInstanceState);

    protected abstract T onCreateViewPager();

}
