package com.jimmy.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jimmy.mvp.helper.PermissionHelper;


/**
 * @author yangyoujun
 */
abstract class AbsActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(savedInstanceState);
    }

    private void checkPermission(Bundle savedInstanceState) {
        if (hasPermission()) {
            onAgree(savedInstanceState);
        } else {
            requestPermission(savedInstanceState);
        }
    }

    protected final boolean hasPermission() {
        if (!PermissionHelper.has()) {
            PermissionHelper.init(this);
        }
        return PermissionHelper.has();
    }

    protected final void setPermission(boolean isRun) {
        PermissionHelper.update(this, isRun);
    }

    // 建议复写
    protected void requestPermission(Bundle savedInstanceState) {
        setPermission(true);
        onAgree(savedInstanceState);
    }

    protected abstract void onAgree(Bundle savedInstanceState);

}
