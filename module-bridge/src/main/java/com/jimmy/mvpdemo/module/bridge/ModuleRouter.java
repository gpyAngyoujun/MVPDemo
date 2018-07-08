package com.jimmy.mvpdemo.module.bridge;

import android.support.v4.util.ArrayMap;

import com.jimmy.mvpdemo.contract.router.IModuleContract;
import com.jimmy.mvpdemo.contract.router.Router;
import com.jimmy.mvpdemo.contract.router.RouterRegistry;
import com.jimmy.mvpdemo.module.empty.EmptyRouter;
import com.jimmy.mvpdemo.module.images.ImagesRouter;

import java.util.Map;

/**
 * @author jimmy
 * @date 2018/7/7
 */
public class ModuleRouter {
    public static ModuleRouter ins() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ModuleRouter INSTANCE = new ModuleRouter();
    }

    private final Map<String, IModuleContract> mRouterMap = new ArrayMap<>();

    private ModuleRouter() {
    }

    public void register() {
        Class[] routerCls = {
                BuiltInModule.class,
                EmptyRouter.class,
                ImagesRouter.class
        };

        for (Class router : routerCls) {
            if (IModuleContract.class.isAssignableFrom(router)) {
                Router routerAnnotation = (Router) router.getAnnotation(Router.class);
                if (routerAnnotation == null) {
                    continue;
                }

                try {
                    mRouterMap.put(routerAnnotation.value(), (IModuleContract) router.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public IModuleContract route(int router) {
        return this.route(String.valueOf(router));
    }

    public IModuleContract route(String router) {
        IModuleContract contract = mRouterMap.get(router);
        if (contract == null) {
            contract = mRouterMap.get(RouterRegistry.BUILTIN);
        }
        return contract;
    }
}
