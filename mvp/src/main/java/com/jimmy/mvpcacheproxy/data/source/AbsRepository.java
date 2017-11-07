package com.jimmy.mvpcacheproxy.data.source;

/**
 * Created by yangyoujun on 17-9-20.
 *
 * @author yangyoujun
 */

public abstract class AbsRepository<R extends IRemoteSource, L extends ILocalSource> implements ISource {

    private final R mRemote;
    private final L mLocal;

    protected AbsRepository(R remote, L local) {
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
