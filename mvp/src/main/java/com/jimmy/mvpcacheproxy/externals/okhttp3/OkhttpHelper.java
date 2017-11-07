package com.jimmy.mvpcacheproxy.externals.okhttp3;

import okhttp3.OkHttpClient;

/**
 * @author yangyoujun
 * @Date 17-11-7
 */
public class OkhttpHelper {

    public static OkHttpClient create() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
        return client;
    }

}
