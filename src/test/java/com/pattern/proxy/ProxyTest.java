/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.proxy;

import org.junit.Test;

/**
 *
 * @author ThinkPad
 */
public class ProxyTest {

    @Test
    public void testProxy() throws Exception {
        Bean mi = new Bean();
        BeanProxy proxy = new BeanProxy<>(mi);
        BeanFactory phone = (BeanFactory) proxy.getProxy();
        phone.getName();
        phone.takePhoto();

    }

}
