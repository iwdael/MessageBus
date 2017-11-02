package com.aliletter.messagebus;



import com.aliletter.messagebus.base.ThreadMode;

import java.lang.reflect.Method;

/**
 * Author: aliletter
 * Github: http://github.com/aliletter
 * Data: 2017/8/19.
 */

public class SubscribleMethod {
    private Method method;
    private Class<?> eventType;
    private ThreadMode mode;

    public SubscribleMethod(Method method, Class<?> eventType, ThreadMode mode) {
        this.method = method;
        this.mode = mode;
        this.eventType = eventType;
    }

    public Method getMethod() {
        return method;
    }

    public ThreadMode getMode() {
        return mode;
    }

    public Class<?> getEventType() {
        return eventType;
    }
}
