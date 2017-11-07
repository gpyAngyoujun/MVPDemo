package com.jimmy.mvpdemo.module.images.data.source;

/**
 * Created by yangyoujun on 17-9-20.
 */

class ImagesSourceHelper {

    static final int FETCH_SIZE = 10;

    static ImagesRepository create() {
        return new ImagesRepository.RepositoryImpl(
                new IImagesRemoteSource.SourceImpl(),
                new IImagesLocalSource.SourceImpl());
    }
}
