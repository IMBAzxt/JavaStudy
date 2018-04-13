/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.factory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 产品工厂，实现工厂接口
 * 1、工厂可以生产各种继承自Product的产品。
 *
 * @author zhengxuetao
 */
public class ProductFactory implements Factory {

    private ProductFactory() {

    }

    private static ProductFactory productFactory = new ProductFactory();

    public static ProductFactory getFactory() {
        return productFactory;
    }

    @Override
    public <T extends Product> T instance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
