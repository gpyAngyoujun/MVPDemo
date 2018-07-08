package com.jimmy.mvpdemo.module.images;

import android.content.Intent;

import com.jimmy.mvpdemo.contract.router.IModuleContract;
import com.jimmy.mvpdemo.contract.router.Router;
import com.jimmy.mvpdemo.contract.router.RouterRegistry;

/**
 * @author jimmy
 * @date 2018/7/8
 */
@Router(RouterRegistry.IMAGES)
public class ImagesRouter extends IModuleContract {
    @Override
    public Object load() {
        return new ImagesView();
    }

    @Override
    public void start(Intent intent) {

    }
}
