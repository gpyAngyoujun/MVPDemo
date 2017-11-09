package com.jimmy.mvp.data.source;

/**
 * Created by yangyoujun on 17-9-20.
 *
 * @author yangyoujun
 */

public abstract class AbsRepository<REMOTE, LOCAL> {

    private final REMOTE mRemote;
    private final LOCAL mLocal;

    protected AbsRepository(REMOTE remote, LOCAL local) {
        this.mRemote = remote;
        this.mLocal = local;
    }

    public REMOTE remote() {
        return mRemote;
    }

    public LOCAL local() {
        return mLocal;
    }
}
