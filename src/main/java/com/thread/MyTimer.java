/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thread;

import java.util.TimerTask;

/**
 *
 * @author ThinkPad
 */
public class MyTimer extends TimerTask {

    @Override
    public void run() {
        System.out.println("定时器打印" + System.currentTimeMillis());
    }

}
