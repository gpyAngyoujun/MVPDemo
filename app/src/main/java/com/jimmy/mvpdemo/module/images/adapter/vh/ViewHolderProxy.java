package com.jimmy.mvpdemo.module.images.adapter.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jimmy.mvpdemo.module.images.ImagesHelper;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

/**
 * @author yangyoujun
 * @Date 17-11-10
 */
public abstract class ViewHolderProxy extends RecyclerView.ViewHolder {
    ViewHolderProxy(View itemView) {
        super(itemView);
    }

    public static ViewHolderProxy create(ViewGroup parent, int viewType) {
        ViewHolderProxy proxy;
        switch (viewType) {
            case ImagesHelper.VIEW_TYPE_CATEGORY:
                proxy = CategoryVH.create(parent);
                break;
            case ImagesHelper.VIEW_TYPE_IMAGE:
                proxy = ImageVH.create(parent);
                break;
            default:
                proxy = null;
        }

        return proxy;
    }

    public abstract void onBindViewHolder(int position, ImagesResp.Results aVoid);

}
