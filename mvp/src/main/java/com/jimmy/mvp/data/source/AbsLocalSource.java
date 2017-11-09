package com.jimmy.mvp.data.source;

/**
 * 本地的数据源
 *
 * @author yangyoujun
 */

public abstract class AbsLocalSource<LOCAL> extends AbsSource<LOCAL> {
    protected AbsLocalSource(Class<LOCAL> cls) {
        super(cls);
    }
}
