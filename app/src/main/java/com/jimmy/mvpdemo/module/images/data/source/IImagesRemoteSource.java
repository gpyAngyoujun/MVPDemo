package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvpcacheproxy.data.api.ApiHelper;
import com.jimmy.mvpcacheproxy.data.source.IRemoteSource;
import com.jimmy.mvpcacheproxy.data.source.ISource;
import com.jimmy.mvpdemo.module.images.data.api.ImagesApi;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import io.reactivex.Observable;

/**
 * Created by yangyoujun on 17-9-20.
 * 远程方法
 */

public interface IImagesRemoteSource extends IRemoteSource, ISource {

    Observable<ImagesResp> fetchImages(int pager);

    class SourceImpl implements IImagesRemoteSource {

        @Override
        public Observable<ImagesResp> fetchImages(int pager) {
            ImagesApi api = ApiHelper.create(ImagesApi.class);
            return api.welfare(ImagesSourceHelper.FETCH_SIZE, pager);
        }
    }
}
