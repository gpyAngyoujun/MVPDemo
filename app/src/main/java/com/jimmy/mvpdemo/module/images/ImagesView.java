package com.jimmy.mvpdemo.module.images;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jimmy.mvpcacheproxy.AbsView;
import com.jimmy.mvpcacheproxy.annotation.BindView;
import com.jimmy.mvpdemo.R;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.List;


public class ImagesView extends AbsView<IImages.Presenter, ImagesView.ViewHolder>
        implements IImages.View {

    @Override
    protected IImages.Presenter onCreatePresenter() {
        return new ImagesPresenter(defaultProxy(IImages.View.class));
    }

    @Override
    protected void onInitLongData(Bundle arguments, Bundle savedInstanceState) {

    }

    @Override
    protected int onGetLayoutRes() {
        return R.layout.images_layout_frg;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View rootView, Bundle savedInstanceState) {
        return new ViewHolder(rootView);
    }

    @Override
    protected void onBindListener() {
        mHolder.srlImages.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    protected void onBindDataLogic() {

    }

    @Override
    public void updateImages(List<ImagesResp> images) {

    }

    static class ViewHolder extends AbsView.AbsViewHolder {

        @BindView(R.id.srl_images)
        SwipeRefreshLayout srlImages;

        @BindView(R.id.rv_images)
        RecyclerView rvImages;

        ViewHolder(View rootView) {
            super(rootView);
        }
    }
}
