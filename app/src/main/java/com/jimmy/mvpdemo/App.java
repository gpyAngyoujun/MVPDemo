package com.jimmy.mvpdemo;

import android.app.Application;

import com.jimmy.mvp.external.helper.LogHelper;
import com.jimmy.mvp.external.helper.PermissionHelper;

/**
 * Created by yangyoujun on 17-11-2.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PermissionHelper.ins().init(this);
        LogHelper.setupTAG("MvpProxy");
    }
}
