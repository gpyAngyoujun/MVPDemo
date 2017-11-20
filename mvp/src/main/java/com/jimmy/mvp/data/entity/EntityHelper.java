package com.jimmy.mvp.data.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created on 17-11-2.
 *
 * @author yangyoujun
 */
public final class EntityHelper {
    public static <T extends AbsEntity> String toJson(T entity) {
        return new Gson().toJson(entity);
    }

    public static <T extends AbsEntity> String toJson(List<T> entity) {
        return new Gson().toJson(entity);
    }
}
