package com.jimmy.mvpcacheproxy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jimmy.mvpcacheproxy.annotation.BindView;
import com.jimmy.mvpcacheproxy.proxy.AbsCacheProxy;
import com.jimmy.mvpcacheproxy.proxy.MVPProxyHelper;

import java.lang.reflect.Field;


public abstract class AbsView<P extends IPresenter, VH extends AbsView.AbsViewHolder> extends Fragment
        implements IView {

    protected P mPresenter;
    protected VH mHolder;
    private AbsCacheProxy mMvpCacheProxy;

    private Toast mToast;
    private ProgressDialog mProgress;

    public AbsView() {
        initMvp();
    }

    private void initMvp() {
        mMvpCacheProxy = onCreateCacheProxy();
        if (mMvpCacheProxy == null) {
            mMvpCacheProxy = this.onCreateCacheProxy();
        }
        mPresenter = onCreatePresenter();
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is NULL!");
        }
    }

    /* Override the method, for custom cache proxy */
    protected <PROXY extends AbsCacheProxy> PROXY onCreateCacheProxy() {
        return (PROXY) MVPProxyHelper.create();
    }

    protected final <V extends IView> V defaultProxy(Class<V> vCls) {
        return MVPProxyHelper.proxy(mMvpCacheProxy, vCls);
    }

    /* 默认是关闭懒加载 */
    private boolean mIsLazyLoading = false;

    public Fragment lazyLoading(boolean isLazyLoading) {
        mIsLazyLoading = isLazyLoading;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitLongData(getArguments(), savedInstanceState);
    }


    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mHolder == null) {
            View rootView = inflater.inflate(onGetLayoutRes(), container, false);
            mHolder = onCreateViewHolder(rootView, savedInstanceState);
            if (mHolder == null) {
                throw new NullPointerException("View Holder can not be NULL!");
            }
        }
        return mHolder.rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBindListener();
        onBindDataLogic();
        MVPProxyHelper.bind(mMvpCacheProxy, this);
    }


    @Override
    public void bindProxyFinish() {
        if (mHasShown) {
            onBindProxyFinish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mIsLazyLoading && !mHasShown) {
            userVisibleHint(true);
        }
    }

    /* for lazy load 标记第一次是否显示过 */
    private boolean mHasShown = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        userVisibleHint(isVisibleToUser);
    }

    private void userVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            intoShow();
        } else {
            onHide();
        }
    }

    /**
     * 判断是否首次启动app且直接显示而并非滑动显示改页面,
     * 如果是则关闭懒加载，使得onStart的时候主动加载数据
     * 否则调用show
     */
    private void intoShow() {
        if (!MVPProxyHelper.isBind(mMvpCacheProxy)) {
            mIsLazyLoading = false;
        } else {
            show();
        }
    }

    private void show() {
        if (mHasShown) {
            onShow();
        } else {
            onFirstShow();
            mHasShown = true;
        }
    }

    /*当 fragment 第一次显示到用户眼前的时候，回调次方法，以后都是回调onShow*/
    protected void onFirstShow() {
        mPresenter.setFetchPermission(true);
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        MVPProxyHelper.unBind(mMvpCacheProxy);
        mToast = null;
        if (mProgress != null) {
            mProgress.dismiss();
            mProgress = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
        MVPProxyHelper.destroy(mMvpCacheProxy);
        mMvpCacheProxy = null;
        mHolder = null;
        mHasShown = false;
        super.onDestroy();
    }

    /* 在此处创建presenter，为何要在此处？因为fragment有可能异常挂掉然后自动恢复新的状态，并且不要你来new，自己新出来 */
    protected abstract P onCreatePresenter();

    /* 在此处初始化的对象是需要保留到onDestroy的,例如adapter，数据是可以在内存中很久的 */
    protected void onInitLongData(Bundle arguments, Bundle savedInstanceState) {
    }

    /* 在此处返回 view layout id*/
    protected abstract int onGetLayoutRes();

    /* optional */
    protected abstract VH onCreateViewHolder(View rootView, Bundle savedInstanceState);

    /* 在此处初始化view的listener */
    protected abstract void onBindListener();

    /* 在此处初始化view的数据逻辑*/
    protected abstract void onBindDataLogic();

    /* 只有当view显示过，才会回调此方法 */
    protected void onBindProxyFinish() {
    }

    protected void onShow() {
    }

    protected void onHide() {
    }

    @Override
    public void showToast(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), str, Toast.LENGTH_SHORT);
        }
        mToast.setText(str);
        mToast.show();
    }

    @Override
    public void showToast(int ResId) {
        showToast(getString(ResId));
    }

    @Override
    public void showProgress(String str) {
        if (mProgress == null) {
            mProgress = new ProgressDialog(getContext());
            mProgress.setCancelable(false);
        }
        mProgress.setMessage(str);
        mProgress.show();
    }

    @Override
    public void showProgress(int strId) {
        showProgress(getString(strId));
    }

    @Override
    public void hideProgress() {
        if (mProgress != null) {
            mProgress.dismiss();
        }
    }

    public abstract static class AbsViewHolder {

        protected final View rootView;

        protected AbsViewHolder(View rootView) {
            this.rootView = rootView;
            try {
                parseBindViewFields();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        private void parseBindViewFields() throws IllegalAccessException {
            Field[] fields = getClass().getDeclaredFields();
            for (Field field : fields) {
                BindView bindView = field.getAnnotation(BindView.class);
                if (isViewId(bindView)) {
                    field.setAccessible(true);
                    field.set(this, findView(bindView.value()));
                }
            }
        }

        private boolean isViewId(BindView val) {
            return val != null && (val.value() >>> 24) >= 2;
        }

        protected <T extends View> T findView(int ResId) {
            return (T) rootView.findViewById(ResId);
        }

        protected Context getContext() {
            return rootView.getContext();
        }

    }

}
