package com.jimmy.mvpcacheproxy.data.source;

/**
 * 服务器的数据源
 *
 * @author yangyoujun
 */

public abstract class AbsRemoteSource<REMOTE> extends AbsSource<REMOTE> {
    protected AbsRemoteSource(Class<REMOTE> cls) {
        super(cls);
    }
}
