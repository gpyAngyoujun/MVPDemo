package com.jimmy.mvpdemo.module.images.adapter.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author yangyoujun
 * @Date 17-11-10
 */
public abstract class ViewHolderProxy extends RecyclerView.ViewHolder {
    public ViewHolderProxy(View itemView) {
        super(itemView);
    }

    public static ViewHolderProxy create(ViewGroup parent, int viewType) {
        return null;
    }

    public abstract void onBindViewHolder(int position);
}
