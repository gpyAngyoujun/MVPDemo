package com.jimmy.mvpcacheproxy.data.api;

import com.jimmy.mvpcacheproxy.externals.okhttp3.OkhttpHelper;

import io.reactivex.internal.functions.ObjectHelper;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 17-11-2.
 *
 * @author yangyoujun
 */
public final class ApiHelper {

    public static <T> T create(Class<T> apiCls) {
        ObjectHelper.requireNonNull(apiCls, "ApiHelper-create() apiCls is Null!");

        if (!apiCls.isInterface()) {
            throw new IllegalArgumentException(
                    apiCls.getName() + " is not an interface");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkhttpHelper.create())
                .build();

        return retrofit.create(apiCls);
    }
}
