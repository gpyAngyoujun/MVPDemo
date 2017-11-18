package com.jimmy.mvp;


import android.content.Context;
import android.os.Bundle;

public interface IPresenter {

    void setFetchPermission(boolean isFetch);

    /* 提供给view的调用，内部会选择响应生命周期 */
    void start(Context context, Bundle arguments);

    /* 提供给view的调用，内部会选择响应生命周期 */
    void destroy();

}
