package com.jimmy.mvpdemo.module.images.adapter.vh;

import android.view.View;
import android.view.ViewGroup;

import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

/**
 * @author yangyoujun
 * @Date 17-11-14
 */
class ImageVH extends ViewHolderProxy {

    private ImageVH(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewHolder(int position, ImagesResp.Results aVoid) {

    }

    static ViewHolderProxy create(ViewGroup parent) {
        return null;
    }
}
