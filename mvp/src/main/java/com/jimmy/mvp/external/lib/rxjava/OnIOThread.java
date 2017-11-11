package com.jimmy.mvp.external.lib.rxjava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 本注解需要配合source中的wrap()方法使用,否则无效
 *
 * @author yangyoujun
 * @Date 17-11-8
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnIOThread {
    boolean value() default true;
}
