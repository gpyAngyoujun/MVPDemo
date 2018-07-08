package com.jimmy.mvpdemo.contract.router;

import android.content.Intent;

/**
 * @author jimmy
 * @date 2018/7/7
 */
public abstract class IModuleContract {

    /**
     * 子类中必须要有一个空参数的构造函数，否则无法实例化
     */
    public IModuleContract() {
    }

    /**
     * 加载一个被 @Router 修饰的对象
     * 通过new，将该对象生成
     */
    public abstract Object load();

    /**
     * 开启一个activity
     *
     * @param intent 要传递的intent
     */
    public abstract void start(Intent intent);
}
