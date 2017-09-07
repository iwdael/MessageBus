package com.absurd.messagebus;



import com.absurd.messagebus.base.ThreadMode;

import java.lang.reflect.Method;

/**
 * Author: mr-absurd
 * Github: http://github.com/mr-absurd
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
