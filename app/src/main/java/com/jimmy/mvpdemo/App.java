package com.jimmy.mvpdemo;

import android.app.Application;

import com.jimmy.mvp.external.helper.LogHelper;
import com.jimmy.mvpdemo.module.bridge.ModuleRouter;

/**
 * Created route yangyoujun on 17-11-2.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogHelper.setupTAG("MvpProxy");
        ModuleRouter.ins().register();
    }
}
