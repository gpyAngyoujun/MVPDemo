package com.jimmy.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * @author yangyoujun
 */
abstract class AbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(savedInstanceState);
    }

    private void checkPermission(final Bundle savedInstanceState) {
        if (hasPermission()) {
            onAgree(savedInstanceState);
        } else {
            requestPermission(new AppPermission.Callback() {

                @Override
                public void result(boolean isAgree) {
                    setPermission(isAgree);
                    if (isAgree) {
                        onAgree(savedInstanceState);
                    }
                }
            });
        }
    }

    private boolean hasPermission() {
        return AppPermission.ins().has() || AppPermission.ins().get(this);
    }

    private void setPermission(boolean isAgree) {
        AppPermission.ins().update(this, isAgree);
    }

    // 建议复写
    protected void requestPermission(AppPermission.Callback callback) {
        callback.result(true);
    }

    protected <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 当我们获取到App的开启权限时，回回调此方法
     */
    protected abstract void onAgree(Bundle savedInstanceState);

}
