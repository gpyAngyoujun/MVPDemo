package com.jimmy.mvp;


import android.content.Context;
import android.os.Bundle;

public interface IPresenter {

    /**
     * 调用此方法来启动presenter，默认是关闭的
     */
    void enablePresenter(boolean isEnabled);

    /**
     * 提供给view的调用，内部会选择响应生命周期
     */
    void start(Context context, Bundle arguments);

    /**
     * 提供给view的调用，内部会选择响应生命周期
     */
    void destroy();

}
