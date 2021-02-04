package com.wyh.think.in.spring.scope.demo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：WangYaHao
 * @date ：Created in 2021/2/3 1:42 下午
 */
public class ThreadLocalBeanScope implements Scope {


    public static final String SCOPE_NAME = "thread-local-scope";

    private final ThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal(SCOPE_NAME) {
        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> stringObjectMap = threadLocal.get();
        Object o = stringObjectMap.get(name);
        if (o == null) {
            o = objectFactory.getObject();
            stringObjectMap.put(name, o);
        }
        return o;
    }

    @Override
    public Object remove(String name) {
        threadLocal.get().remove(name);
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return threadLocal.get().get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
