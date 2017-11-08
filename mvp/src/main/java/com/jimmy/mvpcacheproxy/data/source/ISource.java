package com.jimmy.mvpcacheproxy.data.source;

/**
 * @author yangyoujun
 * @Date 17-11-9
 */
public interface ISource<SOURCE_INTERFACE> {

    /**
     * 包装一下当前的源对象,这个包装的方法可以自己定义,这里我默认使用ObservableHelper包装了一下
     *
     * @return 返回当前的源
     */
    SOURCE_INTERFACE wrap();
}
