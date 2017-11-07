package com.jimmy.mvpcacheproxy.externals.okhttp3;

import okhttp3.OkHttpClient;

/**
 * @author yangyoujun
 * @Date 17-11-7
 */
public class OkhttpHelper {

    /**
     * 我们在这里构建一个okhttp3的client,可以添加拦截器等一系列自定义操作
     */
    public static OkHttpClient create() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
        return client;
    }

}
