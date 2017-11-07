package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvpcacheproxy.data.source.AbsSource;

/**
 * Created by yangyoujun on 17-9-17.
 */

public class ImagesSource extends AbsSource<IImagesRemoteSource, IImagesLocalSource> implements IImagesSource {

    private static ImagesSource mInstance = null;

    public static ImagesSource ins() {
        if (mInstance == null) {
            synchronized (ImagesSource.class) {
                if (mInstance == null) {
                    mInstance = ImagesSourceHelper.create();
                }
            }
        }
        return mInstance;
    }

    ImagesSource(IImagesRemoteSource remote, IImagesLocalSource local) {
        super(remote, local);
    }

}
