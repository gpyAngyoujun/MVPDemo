package com.jimmy.mvp.external.lib.rxjava;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jimmy.mvp.helper.LogHelper;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;

/**
 * @author yangyoujun
 * @Date 17-11-3
 */
class DisposableManager {
    private static DisposableManager mInstance = null;

    static DisposableManager ins() {
        if (mInstance == null) {
            synchronized (DisposableManager.class) {
                if (mInstance == null) {
                    mInstance = new DisposableManager();
                }
            }
        }
        return mInstance;
    }

    private final Map<String, CompositeDisposable> mDispose = new WeakHashMap<>();

    private DisposableManager() {
    }

    void onSubscribe(String presenter, Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "DisposableManager-onSubscribe() disposable is NULL!");
        if (TextUtils.isEmpty(presenter)) {
            LogHelper.d("DisposableManager-onSubscribe() presenter is NULL!");
        }
        disposables(presenter).add(disposable);
    }

    void onError(String presenter, Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "DisposableManager-onError() disposable is NULL!");
        disposables(presenter).remove(disposable);
    }

    void onComplete(String presenter, Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "DisposableManager-onComplete() disposable is NULL!");
        disposables(presenter).remove(disposable);
    }

    void dispose(String presenter) {
        CompositeDisposable remove = mDispose.remove(presenter);
        if (remove == null) {
            return;
        }
        remove.dispose();
    }

    @NonNull
    private CompositeDisposable disposables(String presenter) {
        CompositeDisposable disposables = mDispose.get(presenter);
        if (disposables == null) {
            disposables = new CompositeDisposable();
            mDispose.put(presenter, disposables);
        }
        return disposables;
    }
}
