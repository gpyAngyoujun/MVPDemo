package com.jimmy.mvp.external.lib.rxjava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangyoujun
 * @Date 17-11-8
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnWorkThread {
    boolean value() default true;
}
