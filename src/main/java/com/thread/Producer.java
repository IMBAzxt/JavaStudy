/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThinkPad
 */
public class Producer extends Thread {

    private Car car;

    public Producer(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
//            while (true) {
//                car.create();
//            }
            car.read();
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
