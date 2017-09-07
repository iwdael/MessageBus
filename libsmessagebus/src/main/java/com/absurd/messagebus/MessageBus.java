package com.absurd.messagebus;

import android.os.Handler;
import android.os.Looper;

import com.absurd.messagebus.base.Subscribe;
import com.absurd.messagebus.base.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: mr-absurd
 * Github: http://github.com/mr-absurd
 * Data: 2017/8/16.
 */

public class MessageBus {
    private volatile static MessageBus INSTANCE;
    private Map<Object, List<SubscribleMethod>> map;
    private Handler handler;
    private ExecutorService executorService;

    private MessageBus() {
        map = new HashMap<>();
        handler = new Handler(Looper.getMainLooper());
        executorService = Executors.newCachedThreadPool();
    }

    public static MessageBus getBus() {
        if (INSTANCE == null) {
            synchronized (MessageBus.class) {
                if (INSTANCE == null)
                    INSTANCE = new MessageBus();
            }
        }
        return INSTANCE;
    }


    public void register(Object activity) {
        List<SubscribleMethod> list = map.get(activity);
        if (list == null) {
            List<SubscribleMethod> methods = findSubscribleMethod(activity);
            map.put(activity, methods);
        }
    }

    public void unregister(Object activity) {
        if (activity != null)
            map.remove(activity);
    }

    public void send(final Object obj) {
        Set<Object> set = map.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            final Object activity = it.next();
            List<SubscribleMethod> list = map.get(activity);
            for (final SubscribleMethod subscribleMethod : list) {
                if (subscribleMethod.getEventType().isAssignableFrom(obj.getClass())) {
                    switch (subscribleMethod.getMode()) {
                        case MAINTHREAD:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(activity, subscribleMethod, obj);
                            } else {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(activity, subscribleMethod, obj);
                                    }
                                });
                            }
                            break;

                        case POSTTHREAD:
                            invoke(activity, subscribleMethod, obj);
                            break;

                        case BACKGROUND:
                        case ASYN:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(activity, subscribleMethod, obj);
                                    }
                                });
                            } else {
                                invoke(activity, subscribleMethod, obj);
                            }
                            break;
                    }
                }

            }
        }
    }

    private List<SubscribleMethod> findSubscribleMethod(Object activity) {
        List<SubscribleMethod> list = new CopyOnWriteArrayList<>();
        Class<?> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        while (clazz != null) {
            String name = clazz.getName();
            if (name.startsWith("android.") || name.startsWith("java.") || name.startsWith("javax.")) {
                break;
            }
            for (Method method : methods) {
                Subscribe subcribe = method.getAnnotation(Subscribe.class);
                if (subcribe == null) continue;
                Class<?>[] paras = method.getParameterTypes();
                if (paras == null || paras.length != 1)
                    throw new IllegalArgumentException("Message Bus must hava one parameter");
                Class<?> paraClass = paras[0];
                ThreadMode mode = subcribe.value();
                SubscribleMethod subscribleMethod = new SubscribleMethod(method, paraClass, mode);
                boolean isAdd = true;
                for (int i = 0; i < list.size(); i++) {
                    if (subscribleMethod.getMethod().getName().equalsIgnoreCase(list.get(i).getMethod().getName()))
                        isAdd = false;
                }
                if (isAdd)
                    list.add(subscribleMethod);
            }
            clazz = clazz.getSuperclass();
        }

        return list;
    }

    private void invoke(Object activity, SubscribleMethod subscribleMethod, Object obj) {
        try {
            subscribleMethod.getMethod().invoke(activity, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
