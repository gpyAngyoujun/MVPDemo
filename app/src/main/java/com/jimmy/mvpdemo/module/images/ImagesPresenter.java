package com.jimmy.mvpdemo.module.images;

import com.jimmy.mvpcacheproxy.externals.rxjava.AbsRxPresenter;
import com.jimmy.mvpdemo.R;
import com.jimmy.mvpdemo.module.images.data.entity.ImageEntity;
import com.jimmy.mvpdemo.module.images.data.source.ImagesSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        ImagesSource.ins().remote()
                .fetchImages(PAGER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverOnRecycler<ImageEntity>() {
                    @Override
                    public void onNext(ImageEntity imageEntities) {

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
