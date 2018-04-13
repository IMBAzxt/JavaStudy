/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.factory;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 测试工程模式
 *
 * @author zhengxuetao
 */
public class FactoryTest {
   
    @Test
    public void testInstance() {
        System.out.println("factory instance");
        Factory factory = ProductFactory.getFactory();
        Iphone iphone = factory.instance(Iphone.class);
        assertTrue(iphone.getName().equals("Iphone"));
        Honor honor = factory.instance(Honor.class);
        assertTrue(honor.getName().equals("Honor"));
    }

}
