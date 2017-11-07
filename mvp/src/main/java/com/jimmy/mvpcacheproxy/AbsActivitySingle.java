package com.jimmy.mvpcacheproxy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;


public abstract class AbsActivitySingle<T extends AbsFragmentView> extends AbsActivity {

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

    protected abstract T onFragmentView(Bundle savedInstanceState);

    protected abstract String onFragmentTag();

}
