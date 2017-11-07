package com.jimmy.mvpdemo.module.images;

import com.jimmy.mvpcacheproxy.IPresenter;
import com.jimmy.mvpcacheproxy.IView;
import com.jimmy.mvpdemo.module.images.data.entity.ImageEntity;

import java.util.List;

/**
 * Created by yangyoujun on 17-9-15.
 */

interface IImages {

    interface View extends IView {

        void updateImages(List<ImageEntity> images);
    }

    interface Presenter extends IPresenter {

    }
}
