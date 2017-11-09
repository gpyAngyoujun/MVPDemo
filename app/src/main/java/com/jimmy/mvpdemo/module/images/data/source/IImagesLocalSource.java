package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvp.data.source.AbsLocalSource;
import com.jimmy.mvp.data.source.ISource;

/**
 * 本地的图片数据源,来自数据库
 *
 * @author yangyoujun
 */

public interface IImagesLocalSource extends ISource<IImagesLocalSource> {

    class SourceImpl extends AbsLocalSource<IImagesLocalSource> implements IImagesLocalSource {

        SourceImpl() {
            super(IImagesLocalSource.class);
        }
    }
}
