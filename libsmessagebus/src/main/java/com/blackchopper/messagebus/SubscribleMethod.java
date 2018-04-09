package com.blackchopper.messagebus;



import com.blackchopper.messagebus.base.ThreadMode;

import java.lang.reflect.Method;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : MessageBus
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
