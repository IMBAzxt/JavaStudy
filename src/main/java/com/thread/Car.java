/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author ThinkPad
 */
public class Car {

    private int total = 1;
    private int maxSize = 5;
    private LinkedBlockingQueue list = new LinkedBlockingQueue(100);

    public void create() throws InterruptedException {
        if (list.size() < maxSize) {
            list.put(total++);
            System.out.println(Thread.currentThread().getName() + "生成了一个，库存：" + list.size() + " :" + System.currentTimeMillis());
        } else {
            System.out.println(Thread.currentThread().getName() + "库存满了" + System.currentTimeMillis());
        }
    }

    public void delete() throws InterruptedException {
        //list.size()并不同步
        Object o = list.poll();
        if (o != null) {
            System.out.println(Thread.currentThread().getName() + "消费了" + o + "，库存：" + list.size() + " :" + System.currentTimeMillis());
        } else {
            System.out.println(Thread.currentThread().getName() + "消费完了" + System.currentTimeMillis());
        }
    }

    private ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();

    public void read() throws InterruptedException {
        lock1.readLock().lock();
        try {
            System.out.println("读锁" + System.currentTimeMillis());
            Thread.sleep(1000);
        } finally {
            lock1.readLock().unlock();
        }
    }

    public void write() throws InterruptedException {
        lock1.writeLock().lock();
        try {
            System.out.println("写锁" + System.currentTimeMillis());
            Thread.sleep(1000);
        } finally {
            lock1.writeLock().unlock();
        }
    }

}
