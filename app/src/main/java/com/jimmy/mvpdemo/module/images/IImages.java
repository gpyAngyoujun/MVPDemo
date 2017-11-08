package com.jimmy.mvpdemo.module.images;

import com.jimmy.mvpcacheproxy.IPresenter;
import com.jimmy.mvpcacheproxy.IView;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.List;

/**
 * Created by yangyoujun on 17-9-15.
 */

interface IImages {

    interface View extends IView {

        /**
         * 设置从repository中得到的images
         */
        void setImages(List<ImagesResp.Results> images);
    }

    interface Presenter extends IPresenter {

    }
}
