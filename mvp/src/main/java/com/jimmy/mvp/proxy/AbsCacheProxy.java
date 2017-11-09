package com.jimmy.mvp.proxy;


import com.jimmy.mvp.IView;
import com.jimmy.mvp.annotation.CacheMethod;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * @author yangyoujun
 */
public abstract class AbsCacheProxy implements InvocationHandler {

    /* 如果是weakhashmap。fragment destroy view就会回收数据了 */
    private final Map<Method, Object[]> mViewCaches = new HashMap<>();
    private WeakReference<IView> mView;

    <V extends IView> V proxy(Class<V> viewClass) {
        if (viewClass == null) {
            throw new NullPointerException("Proxy View Class is NULL!");
        }
        return (V) Proxy.newProxyInstance(viewClass.getClassLoader(), new Class[]{viewClass}, this);
    }

    void bind(IView view) {
        if (view == null) {
            return;
        }
        unBind();
        mView = new WeakReference<>(view);

        for (Method method : mViewCaches.keySet()) {
            invokeMethod(view, method, mViewCaches.get(method));
        }

        view.bindProxyFinish();
    }

    void unBind() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    boolean isBind() {
        return mView != null && mView.get() != null;
    }

    void destroy() {
        unBind();
        mViewCaches.clear();
        onDestroy();
    }

    /* 请在此处释放和清理资源 */
    protected abstract void onDestroy();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (isCacheMethod(method)) {
            cacheMethod(method, args);
        }

        if (mView != null && mView.get() != null) {
            return invokeMethod(mView.get(), method, args);
        }
        return null;
    }

    protected boolean isCacheMethod(Method method) {
        CacheMethod cacheMethod = method.getAnnotation(CacheMethod.class);
        return cacheMethod != null && cacheMethod.isCached();
    }

    protected void cacheMethod(Method method, Object[] args) {
        mViewCaches.put(method, args);
    }

    protected Object invokeMethod(Object handlerView, Method method, Object[] args) {
        if (handlerView == null || method == null) {
            return null;
        }
        try {
            return method.invoke(handlerView, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
