package com.jimmy.mvpdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.jimmy.mvpcacheproxy.AbsActivityContainer;
import com.jimmy.mvpcacheproxy.widget.MultiplePagerContainer;
import com.jimmy.mvpdemo.module.images.ImagesView;

public class MainActivity extends AbsActivityContainer {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_images:
                    setCurrentItem(0);
                    return true;
                case R.id.navigation_text_image:
                    setCurrentItem(1);
                    return true;
                case R.id.navigation_information:
                    setCurrentItem(2);
                    return true;
                default:
                    return false;
            }
        }

    };

    private BottomNavigationView navigation;
    private final int PAGER_SIZE = 3;

    @Override
    protected void requestPermission(final Bundle savedInstanceState) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.permission_reminder)
                .setMessage(R.string.meitu_premission_prompt_desc)
                .setCancelable(false)
                .setPositiveButton(R.string.agree,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                setPermission(true);
                                onAgree(savedInstanceState);
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                .show();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected MultiplePagerContainer onCreateViewPager() {
        return (MultiplePagerContainer) findViewById(R.id.multiple_content);
    }

    @Override
    public int onGetPagerCount() {
        return PAGER_SIZE;
    }

    @Override
    public Fragment onCreateFragmentViewItem(int position) {
        switch (position) {
            case 0:
                return new ImagesView().lazyLoading(true);
            default:
                return new Fragment();
        }
    }

    @Override
    public void onPageSelected(int position) {
        navigation.setSelectedItemId(getItemId(position));
    }

    private int getItemId(int position) {
        switch (position) {
            case 0:
                return R.id.navigation_images;
            case 1:
                return R.id.navigation_text_image;
            case 2:
                return R.id.navigation_information;
            default:
                return R.id.navigation_images;
        }
    }
}
