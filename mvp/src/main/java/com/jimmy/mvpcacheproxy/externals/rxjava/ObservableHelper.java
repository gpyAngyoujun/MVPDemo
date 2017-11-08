package com.jimmy.mvpcacheproxy.externals.rxjava;

import com.jimmy.mvpcacheproxy.data.entity.AbsEntity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yangyoujun
 * @Date 17-11-8
 */
public class ObservableHelper {

    public static <T extends AbsEntity> Observable<T> wrap(Observable<T> observable) {

        ObjectHelper.requireNonNull(observable, "ObservableHelper-wrap() observable is NULL!");

        Predicate<T> predicate = new Predicate<T>() {
            @Override
            public boolean test(T imagesResp) throws Exception {
                return imagesResp.isSuccessfully();
            }
        };

        return observable.filter(predicate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
