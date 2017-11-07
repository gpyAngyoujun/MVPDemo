package com.jimmy.mvpdemo.module.images.data.source;

/**
 * Created by yangyoujun on 17-9-20.
 */

class ImagesSourceHelper {

    static final int FETCH_SIZE = 10;

    static ImagesSource create() {
        return new ImagesSource(new IImagesRemoteSource.SourceImpl(), new IImagesLocalSource.SourceImpl());
    }
}
