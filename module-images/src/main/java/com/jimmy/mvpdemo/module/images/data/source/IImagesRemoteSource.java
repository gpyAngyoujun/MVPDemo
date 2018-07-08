package com.jimmy.mvpdemo.module.images.data.source;

import com.jimmy.mvpdemo.module.images.Cons;
import com.jimmy.mvpdemo.module.images.data.api.ImagesApi;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangyoujun on 17-9-20.
 * 远程方法
 *
 * @author yangyoujun
 */

public interface IImagesRemoteSource {

    /**
     * 获取图片数据集合
     *
     * @param pager 页,表示要获取第几页的数据
     * @return 返回一个可观测的闭包
     */
    Observable<ImagesResp> fetchImages(int pager);

    class SourceImpl implements IImagesRemoteSource {

        private ImagesApi mImagesApi;

        @Override
        public Observable<ImagesResp> fetchImages(int pager) {
            ImagesApi api = create(ImagesApi.class, Cons.URL);
            return api.welfare(ImagesSourceHelper.FETCH_SIZE, pager);
        }

        private ImagesApi create(Class<ImagesApi> cls, String url) {
            if (mImagesApi == null) {
                mImagesApi = new Retrofit.Builder()
                        .baseUrl(url)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(cls);
            }
            return mImagesApi;
        }

    }
}
