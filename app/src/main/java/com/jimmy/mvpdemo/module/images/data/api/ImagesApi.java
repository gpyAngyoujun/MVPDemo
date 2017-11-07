package com.jimmy.mvpdemo.module.images.data.api;


import com.jimmy.mvpdemo.module.images.data.entity.ImageEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yangyoujun on 17-9-17.
 */

public interface ImagesApi {

    @GET("api/data/%E7%A6%8F%E5%88%A9/{SIZE}/{PAGER}")
    Observable<ImageEntity> welfare(@Path("SIZE") int size, @Path("PAGER") int pager);

}
