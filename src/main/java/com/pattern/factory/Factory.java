/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.factory;

/**
 * 工厂模式-工厂接口
 * 
 * @author zhengxuetao
 */
public interface Factory {

    <T extends Product> T instance(Class<T> clazz);

}
