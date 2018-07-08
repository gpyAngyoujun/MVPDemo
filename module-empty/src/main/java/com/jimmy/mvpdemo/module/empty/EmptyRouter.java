package com.jimmy.mvpdemo.module.empty;

import android.content.Intent;

import com.jimmy.mvpdemo.contract.router.IModuleContract;
import com.jimmy.mvpdemo.contract.router.Router;
import com.jimmy.mvpdemo.contract.router.RouterRegistry;

/**
 * @author jimmy
 * @date 2018/7/8
 */
@Router(RouterRegistry.EMPTY)
public class EmptyRouter extends IModuleContract {

    @Override
    public Object load() {
        return new EmptyView();
    }

    @Override
    public void start(Intent intent) {

    }
}
