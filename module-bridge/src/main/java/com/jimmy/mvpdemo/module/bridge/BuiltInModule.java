package com.jimmy.mvpdemo.module.bridge;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jimmy.mvpdemo.contract.router.IModuleContract;
import com.jimmy.mvpdemo.contract.router.Router;
import com.jimmy.mvpdemo.contract.router.RouterRegistry;

/**
 * @author jimmy
 * @date 2018/7/8
 */
@Router(RouterRegistry.BUILTIN)
class BuiltInModule extends IModuleContract {
    @Override
    public Object load() {
        return new Fragment();
    }

    @Override
    public void start(Intent intent) {

    }
}
