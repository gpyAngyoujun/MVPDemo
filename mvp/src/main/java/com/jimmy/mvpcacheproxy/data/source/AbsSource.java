package com.jimmy.mvpcacheproxy.data.source;

/**
 * Created by yangyoujun on 17-9-20.
 *
 * @author yangyoujun
 */

public abstract class AbsSource<R extends IRemoteSource, L extends ILocalSource> implements ISource {

    private final R mRemote;
    private final L mLocal;

    protected AbsSource(R remote, L local) {
        this.mRemote = remote;
        this.mLocal = local;
    }

    public R remote() {
        return mRemote;
    }

    public L local() {
        return mLocal;
    }
}
