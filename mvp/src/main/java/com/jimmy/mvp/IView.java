package com.jimmy.mvp;

/**
 * 该类用于传递vProxy
 */

public interface IView {

    void bindProxyFinish();

    void showToast(String str);

    void showToast(int ResId);

    void showProgress(String str);

    void showProgress(int strId);

    void hideProgress();
}
