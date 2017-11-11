package com.jimmy.mvpdemo.module.images.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jimmy.mvpdemo.module.images.adapter.vh.ViewHolderProxy;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyoujun
 * @Date 17-11-9
 */
public class ImagesAdapter extends RecyclerView.Adapter<ViewHolderProxy> {

    private final List<ImagesResp.Results> mImages = new ArrayList<>();

    @Override
    public ViewHolderProxy onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderProxy.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolderProxy holder, int position) {
        holder.onBindViewHolder(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setImages(List<ImagesResp.Results> images) {
        if (images != null) {
            mImages.clear();
            mImages.addAll(images);
            notifyDataSetChanged();
        }
    }

}
