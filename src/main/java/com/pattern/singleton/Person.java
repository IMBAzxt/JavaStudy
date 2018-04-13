/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pattern.singleton;

/**
 * 单例模式设计
 * 在instance里面初始化会出现线程不安全。
 *
 * @author zhengxuetao
 */
public class Person {

    private Person() {

    }

    private static Person person = new Person();

    public static Person instance() {
        if (person == null) {
            person = new Person();
        }
        return person;
    }

}
