package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvpcacheproxy.data.source.ILocalSource;

/**
 * Created by yangyoujun on 17-9-20.
 * 本地方法
 */

public interface IImagesLocalSource extends ILocalSource, IImagesSource {

    class SourceImpl implements IImagesLocalSource {

    }
}
