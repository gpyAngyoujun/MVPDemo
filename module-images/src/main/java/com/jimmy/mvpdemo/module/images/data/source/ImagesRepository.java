package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvp.external.source.AbsRepository;

/**
 * Created by yangyoujun on 17-9-17.
 */

public abstract class ImagesRepository extends AbsRepository<IImagesRemoteSource, IImagesLocalSource> {

    public static ImagesRepository ins() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ImagesRepository INSTANCE = ImagesSourceHelper.create();
    }

    private ImagesRepository(IImagesRemoteSource remote, IImagesLocalSource local) {
        super(remote, local);
    }

    static class RepositoryImpl extends ImagesRepository {

        RepositoryImpl(IImagesRemoteSource remote, IImagesLocalSource local) {
            super(remote, local);
        }
    }

}
