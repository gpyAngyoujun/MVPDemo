package com.jimmy.mvpdemo.module.images;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.jimmy.mvp.AbsView;
import com.jimmy.mvpdemo.contract.router.Router;
import com.jimmy.mvpdemo.contract.router.RouterRegistry;
import com.jimmy.mvpdemo.module.images.adapter.ImagesAdapter;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.List;

@Router(RouterRegistry.IMAGES)
public class ImagesView extends AbsView<IImages.Presenter, ViewHolder>
        implements IImages.View {

    private ImagesAdapter mAdapter;

    @Override
    protected IImages.Presenter onCreatePresenter() {
        return new ImagesPresenter(defaultProxy(IImages.View.class));
    }

    @Override
    protected void onInitLongData(Bundle arguments, Bundle savedInstanceState) {
        mAdapter = new ImagesAdapter();
    }

    @Override
    protected int onBindingLayoutRes() {
        return R.layout.images_layout_frg;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View rootView, Bundle savedInstanceState) {
        return new ViewHolder(rootView);
    }

    @Override
    protected void onBindingListener() {
        mHolder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start(getContext(), getArguments());
            }
        });
    }

    @Override
    protected void onBindDataLogic() {
        mHolder.bindRecyclerView(mAdapter);
    }

    @Override
    public void setImages(List<ImagesResp.Results> images) {
        mHolder.setRefreshing(false);
        mAdapter.setImages(images);
    }

    @Override
    public void onEmptyPage() {

    }

}

class ViewHolder extends AbsView.AbsViewHolder {

    private static final int SPAN_COUNT = 4;

    /**
     * //    @BindView(R.id.srl_images)
     */
    private SwipeRefreshLayout srlImages;

    /**
     * //    @BindView(R.id.rv_images)
     */
    private RecyclerView rvImages;

    /**
     * //    @BindView(R.id.pb_loading)
     */
    private ProgressBar pbLoading;

    ViewHolder(View rootView) {
        super(rootView);
        srlImages = findView(R.id.srl_images);
        rvImages = findView(R.id.rv_images);
        pbLoading = findView(R.id.pb_loading);
    }

    void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        srlImages.setOnRefreshListener(listener);
    }

    void setRefreshing(boolean isRfreshing) {
        srlImages.setRefreshing(isRfreshing);
    }

    void bindRecyclerView(ImagesAdapter adapter) {
        GridLayoutManager layout = new GridLayoutManager(getContext(), SPAN_COUNT);
        rvImages.setLayoutManager(layout);
        rvImages.setHasFixedSize(true);
        rvImages.setAdapter(adapter);
        rvImages.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 0;
            }
        });
    }

    void showProgressbar(boolean isShow) {
        pbLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}