package com.jimmy.mvpcacheproxy;


public abstract class AbsPresenter<VP extends IView> implements IPresenter {

    /* deprecate
      1. 这个VM对象不销毁，因为p在异步回来的时候，v已经挂了，但是vm还在，让数据正常的更新到vm，没有泄露且没有循环引用
      2. 这个vm是v的代理对象
    */
    protected final VP mViewProxy;
    private boolean mIsFetch;

    protected AbsPresenter(VP viewProxy) {
        mViewProxy = viewProxy;
    }

    @Override
    public final void setFetchPermission(boolean isFetch) {
        mIsFetch = isFetch;
    }

    @Override
    public final void start() {
        if (mIsFetch) {
            onStart();
        }
    }

    @Override
    public void destroy() {
        mIsFetch = false;
        onDestroy();
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

    /*请在该方法中判断数据是否过期,生命周期的响应*/
    protected abstract void onStart();

    /*请在该方法中回收所有的对象,生命周期的响应*/
    protected abstract void onDestroy();
}
