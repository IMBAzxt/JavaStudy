/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.proxy;

/**
 *
 * @author ThinkPad
 */
public class Bean implements BeanFactory {

    @Override
    public String getName() {
        System.out.println("xiaomi");
        return "xiaomi";
    }

    @Override
    public void takePhoto() {
        System.out.println("take a picture");
    }

}
