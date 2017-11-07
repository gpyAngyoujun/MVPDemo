package com.jimmy.mvpcacheproxy;


public interface IPresenter {

    void setFetchPermission(boolean isFetch);

    /* 提供给view的调用，内部会选择响应生命周期 */
    void start();

    /* 提供给view的调用，内部会选择响应生命周期 */
    void destroy();

}
