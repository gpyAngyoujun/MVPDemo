package com.jimmy.mvpdemo.module.images.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jimmy.mvpdemo.module.images.adapter.vh.ViewHolderProxy;
import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.List;

/**
 * 你可能会很奇怪,为什么我要吧adapter中的数据源单独分离,吧viewholder也单独分解
 * 如果你的item是多样的,且你的数据源也是多样化的,那这样分离会搞笑很多
 * 以及数据的扩展性也会变得很强
 * 还有一点就是,adapter是一个适配器,它的职责是将2个或者若干个毫不相干的对象结合起来.而非存储和编写逻辑
 *
 * @author yangyoujun
 * @Date 17-11-9
 */
public class ImagesAdapter extends RecyclerView.Adapter<ViewHolderProxy> {

    private ImagesDataPackage mImages;

    public ImagesAdapter() {
        mImages = ImagesDataPackage.ins();
    }

    @Override
    public ViewHolderProxy onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderProxy.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolderProxy holder, int position) {
        holder.onBindViewHolder(position, mImages.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mImages.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setImages(List<ImagesResp.Results> images) {
        if (mImages.setImages(images)) {
            notifyDataSetChanged();
        }
    }

}
