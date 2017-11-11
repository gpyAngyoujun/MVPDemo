package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvp.data.api.ApiHelper;
import com.jimmy.mvp.data.source.AbsRemoteSource;
import com.jimmy.mvp.data.source.ISource;
import com.jimmy.mvp.external.lib.rxjava.OnIOThread;
import com.jimmy.mvpdemo.common.Cons;
import com.jimmy.mvpdemo.module.images.data.api.ImagesApi;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import io.reactivex.Observable;

/**
 * Created by yangyoujun on 17-9-20.
 * 远程方法
 *
 * @author yangyoujun
 */

public interface IImagesRemoteSource extends ISource<IImagesRemoteSource> {

    /**
     * 获取图片数据集合
     *
     * @param pager 页,表示要获取第几页的数据
     * @return 返回一个可观测的闭包
     */
    @OnIOThread
    Observable<ImagesResp> fetchImages(int pager);

    class SourceImpl extends AbsRemoteSource<IImagesRemoteSource> implements IImagesRemoteSource {

        SourceImpl() {
            super(IImagesRemoteSource.class);
        }

        @Override
        public Observable<ImagesResp> fetchImages(int pager) {
            ImagesApi api = ApiHelper.create(ImagesApi.class, Cons.URL);
            return api.welfare(ImagesSourceHelper.FETCH_SIZE, pager);
        }

    }
}
