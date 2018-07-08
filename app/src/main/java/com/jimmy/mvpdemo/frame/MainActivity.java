package com.jimmy.mvpdemo.frame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.jimmy.mvp.AbsActivityContainer;
import com.jimmy.mvp.AppPermission;
import com.jimmy.mvp.widget.MultiplePagerContainer;
import com.jimmy.mvpdemo.R;
import com.jimmy.mvpdemo.module.bridge.ModuleRouter;

/**
 * @author jimmy
 */
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
    private static final int PAGER_SIZE = 3;

    @Override
    protected void requestPermission(final AppPermission.Callback callback) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.permission_reminder)
                .setMessage(R.string.meitu_premission_prompt_desc)
                .setCancelable(false)
                .setPositiveButton(R.string.agree,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                callback.result(true);
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                callback.result(false);
                                finish();
                            }
                        })
                .show();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        navigation = findView(R.id.navigation);
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
        return (Fragment) ModuleRouter.ins().route(position).load();
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
