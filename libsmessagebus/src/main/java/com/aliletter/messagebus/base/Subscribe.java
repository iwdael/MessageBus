package com.aliletter.messagebus.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: aliletter
 * Github: http://github.com/aliletter
 * Data: 2017/8/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    ThreadMode value() default ThreadMode.POSTTHREAD;
}
