package com.jimmy.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yangyoujun on 17-8-29.
 */

public class MultiplePagerAdapter<T extends IMultiplePagerDataSource> extends FragmentPagerAdapter {

    protected T mDataSour;

    public MultiplePagerAdapter(FragmentManager fm, T dataSour) {
        super(fm);
        mDataSour = dataSour;
    }


    @Override
    public int getCount() {
        if (mDataSour != null) {
            return mDataSour.onGetPagerCount();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        if (mDataSour != null) {
            return mDataSour.onCreateFragmentViewItem(position);
        }
        return new Fragment();
    }

    public void onDestroy() {
        mDataSour = null;
    }
}
