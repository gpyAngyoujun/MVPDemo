package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvpcacheproxy.data.source.ILocalSource;
import com.jimmy.mvpcacheproxy.data.source.ISource;

/**
 * Created by yangyoujun on 17-9-20.
 * 本地方法
 */

public interface IImagesLocalSource extends ILocalSource, ISource {

    class SourceImpl implements IImagesLocalSource {

    }
}
