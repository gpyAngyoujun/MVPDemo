package com.jimmy.mvpcacheproxy.adapter;

import android.support.v4.app.Fragment;

/**
 * Created by yangyoujun on 17-9-4.
 */

public interface IMultiplePagerDataSource {

    int onGetPagerCount();

    /* 在需要的时候才加载 */
    Fragment onCreateFragmentViewItem(int position);
}
