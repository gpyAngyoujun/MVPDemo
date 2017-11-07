package com.jimmy.mvpcacheproxy.externals.rxjava;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jimmy.mvpcacheproxy.helper.LogHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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

    private final Map<String, List<Disposable>> mDispose = new WeakHashMap<>();

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

    void disposeAll(String presenter) {
        List<Disposable> remove = mDispose.remove(presenter);
        if (remove == null) {
            return;
        }
        Observable.fromIterable(remove)
                .subscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        LogHelper.i("DisposableManager-accept: ", "disposeAll free failed?");
                    }
                });
    }

    @NonNull
    private List<Disposable> disposables(String presenter) {
        List<Disposable> disposables = mDispose.get(presenter);
        if (disposables == null) {
            disposables = new ArrayList<>();
            mDispose.put(presenter, disposables);
        }
        return disposables;
    }
}
