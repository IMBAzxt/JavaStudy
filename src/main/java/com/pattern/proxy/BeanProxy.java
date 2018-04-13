/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author ThinkPad
 */
public class BeanProxy<T> implements InvocationHandler {

    T t;

    public BeanProxy() {
        super();
    }

    public BeanProxy(T t) {
        super();
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行的方法" + method.getName());
        System.out.println("方法开始执行");
        Object result = method.invoke(t, args);
        System.out.println("方法执行完成");
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                t.getClass().getInterfaces(), this);
    }
}
