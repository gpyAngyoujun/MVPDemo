package com.jimmy.mvpcacheproxy.proxy;

import com.jimmy.mvpcacheproxy.IView;


/**
 * @author yangyoujun
 */
public class MVPProxyHelper {

    public static AbsCacheProxy create() {
        return MVPCacheProxyInternal.create();
    }

    public static <V extends IView> V proxy(AbsCacheProxy proxy, Class<V> cls) {
        return proxy != null ? proxy.proxy(cls) : null;
    }

    public static <V extends IView> void bind(AbsCacheProxy proxy, V view) {
        if (proxy != null) {
            proxy.bind(view);
        }
    }

    public static boolean isBind(AbsCacheProxy proxy) {
        return proxy != null && proxy.isBind();
    }

    public static void unBind(AbsCacheProxy proxy) {
        if (proxy != null) {
            proxy.unBind();
        }
    }

    public static void destroy(AbsCacheProxy proxy) {
        if (proxy != null) {
            proxy.destroy();
        }
    }
}
