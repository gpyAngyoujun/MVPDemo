package com.jimmy.mvpdemo.module.images.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jimmy.mvpdemo.module.images.R;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyoujun
 * @Date 17-11-9
 */
public class ImagesAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<ImagesResp.Results> mDatas = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.images_card_layout_rv_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setImages(List<ImagesResp.Results> images) {
        if (images != null && !images.isEmpty()) {
            mDatas.clear();
            mDatas.addAll(images);
        }
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    ViewHolder(View itemView) {
        super(itemView);
    }
}
