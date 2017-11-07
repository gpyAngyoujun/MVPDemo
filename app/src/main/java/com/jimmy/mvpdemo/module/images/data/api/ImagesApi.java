package com.jimmy.mvpdemo.module.images.data.api;


import com.jimmy.mvpdemo.module.images.data.entity.ImagesResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yangyoujun on 17-9-17.
 */

public interface ImagesApi {

    @GET("api/data/%E7%A6%8F%E5%88%A9/{SIZE}/{PAGER}")
    Observable<ImagesResp> welfare(@Path("SIZE") int size, @Path("PAGER") int pager);

}
