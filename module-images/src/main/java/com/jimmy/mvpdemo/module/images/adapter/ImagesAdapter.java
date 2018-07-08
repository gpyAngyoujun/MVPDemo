package com.jimmy.mvpdemo.module.images.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.jimmy.mvpdemo.module.images.R;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyoujun
 * @date 17-11-9
 */
public class ImagesAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<ImagesResp.Results> mDatas = new ArrayList<>();
    private final ColorDrawable placeholder = new ColorDrawable(Color.GRAY);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.images_card_layout_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImagesResp.Results results = mDatas.get(position);
        ImageView img = holder.itemView.findViewById(R.id.iv_image);
        Glide.with(holder.itemView)
                .load(results.getUrl())
                .apply(new RequestOptions().placeholder(placeholder))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setImages(List<ImagesResp.Results> images) {
        if (images != null && !images.isEmpty()) {
            mDatas.addAll(0, images);
            notifyDataSetChanged();
        }
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    ViewHolder(View itemView) {
        super(itemView);
    }
}
