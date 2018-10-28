package com.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写分离锁
 *
 * @author zhengxt
 */
public class TestReentrantReadWriteLock {
    public static void main(String[] args) {
        final TestReentrantReadWriteLock testReentrantReadWriteLock = new TestReentrantReadWriteLock();

        new Thread(){
            @Override
            public void run() {
                try {
                    testReentrantReadWriteLock.write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    testReentrantReadWriteLock.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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
