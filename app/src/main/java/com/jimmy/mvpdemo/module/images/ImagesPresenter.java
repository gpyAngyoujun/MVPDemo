package com.jimmy.mvpdemo.module.images;

import com.jimmy.mvp.external.lib.rxjava.AbsRxPresenter;
import com.jimmy.mvp.external.helper.LogHelper;
import com.jimmy.mvpdemo.R;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;
import com.jimmy.mvpdemo.module.images.data.source.ImagesRepository;

import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by yangyoujun on 17-9-16.
 */

class ImagesPresenter extends AbsRxPresenter<IImages.View> implements IImages.Presenter {

    private int PAGER = 1;

    ImagesPresenter(IImages.View viewProxy) {
        super(viewProxy);
    }

    @Override
    protected void onStart() {

        ImagesRepository.ins()
                .remote()
                .wrap()
                .fetchImages(PAGER)
                .map(new Function<ImagesResp, List<ImagesResp.Results>>() {
                    @Override
                    public List<ImagesResp.Results> apply(ImagesResp imagesResp) throws Exception {
                        return imagesResp.getResults();
                    }
                })
                .subscribe(new ObserverOnRecycler<List<ImagesResp.Results>>() {
                    @Override
                    public void onNext(List<ImagesResp.Results> imageEntities) {
                        PAGER++;
                        mViewProxy.setImages(imageEntities);
                        LogHelper.i(imageEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mViewProxy.showToast(R.string.app_name);
                    }
                });
    }

    @Override
    protected void onDestroy() {

    }
}
