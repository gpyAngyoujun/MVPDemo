package com.jimmy.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

/**
 * @param <T> view
 * @author yangyoujun
 */
public abstract class AbsActivitySingle<T extends AbsView> extends AbsActivity {

    @Override
    protected final void onAgree(Bundle savedInstanceState) {
        String tag = onFragmentTag();
        if (TextUtils.isEmpty(tag)) {
            throw new NullPointerException("the tag of fragment-view is empty or NULL!");
        }

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = onFragmentView(savedInstanceState);
        }

        if (fragment == null) {
            throw new NullPointerException("the fragment-view is NULL!");
        }

        manager.beginTransaction()
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

    /**
     * 返回一个view对象, 该对象是view且继承AbsView
     */
    protected abstract T onFragmentView(Bundle savedInstanceState);

    /**
     * 创建fragment的时候附带的tag
     *
     * @return fragment的tag
     */
    protected abstract String onFragmentTag();

}
