package com.jimmy.mvpcacheproxy.data.source;

/**
 * 最基础的数据源对象
 *
 * @author yangyoujuns
 */

abstract class AbsSource<SOURCE_INTERFACE> implements ISource<SOURCE_INTERFACE> {

    private final Class<SOURCE_INTERFACE> mInterface;

    protected AbsSource(Class<SOURCE_INTERFACE> cls) {
        mInterface = cls;
    }

    @Override
    public SOURCE_INTERFACE wrap() {
        return SourceHelper.wrap(this, mInterface);
    }
}
