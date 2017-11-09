package com.jimmy.mvp.data.source;

import com.jimmy.mvp.data.entity.AbsEntity;
import com.jimmy.mvp.external.lib.rxjava.ObservableHelper;
import com.jimmy.mvp.external.lib.rxjava.OnWorkThread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.reactivex.Observable;

/**
 * Created on 17-11-2.
 *
 * @author yangyoujun
 */

public final class SourceHelper {

    static <SOURCE_INTERFACE> SOURCE_INTERFACE wrap(final AbsSource<SOURCE_INTERFACE> sourceImpl, Class<SOURCE_INTERFACE> sourceInterface) {

        return (SOURCE_INTERFACE) Proxy.newProxyInstance(sourceInterface.getClassLoader(),
                new Class[]{sourceInterface},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = method.invoke(sourceImpl, args);
                        if (isWrap(method) && invoke instanceof Observable) {
                            return ObservableHelper.wrap((Observable<AbsEntity>) invoke);
                        }
                        return invoke;
                    }

                    private boolean isWrap(Method method) {
                        OnWorkThread onWorkThread = method.getAnnotation(OnWorkThread.class);
                        return onWorkThread != null && onWorkThread.value();
                    }
                });
    }
}
