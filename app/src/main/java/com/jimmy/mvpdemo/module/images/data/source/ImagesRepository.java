package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvp.data.source.AbsRepository;

/**
 * Created by yangyoujun on 17-9-17.
 */

public abstract class ImagesRepository extends AbsRepository<IImagesRemoteSource, IImagesLocalSource> {

    private static ImagesRepository mInstance = null;

    public static ImagesRepository ins() {
        if (mInstance == null) {
            synchronized (ImagesRepository.class) {
                if (mInstance == null) {
                    mInstance = ImagesSourceHelper.create();
                }
            }
        }
        return mInstance;
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
