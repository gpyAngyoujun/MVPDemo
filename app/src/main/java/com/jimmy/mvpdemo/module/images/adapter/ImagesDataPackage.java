package com.jimmy.mvpdemo.module.images.adapter;

import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Images的数据缓存集合
 *
 * @author yangyoujun
 * @Date 17-11-14
 */
public class ImagesDataPackage {

    private static ImagesDataPackage mInstance = null;

    public static ImagesDataPackage ins() {
        if (mInstance == null) {
            synchronized (ImagesDataPackage.class) {
                if (mInstance == null) {
                    mInstance = new ImagesDataPackage();
                }
            }
        }
        return mInstance;
    }

    private final List<ImagesResp.Results> mImages = new ArrayList<>();

    private ImagesDataPackage() {
    }

    public int size() {
        return mImages.size();
    }

    boolean setImages(List<ImagesResp.Results> images) {
        if (images != null) {
            mImages.clear();
            mImages.addAll(images);
            return true;
        }
        return false;
    }

    ImagesResp.Results get(int position) {
        return (mImages.size() > position && position > -1) ? mImages.get(position) : null;
    }

    public int getItemViewType(int position) {
        return 0;
    }
}
