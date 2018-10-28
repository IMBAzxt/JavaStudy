package com.thread;

/**
 * 重入锁实现
 */
public class ReInLock {

    public static void main(String[] args) {
        final ReInLock ri = new ReInLock();

        new Thread() {
            @Override
            public void run() {
                ri.speed("驾驶员");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                ri.slow("副驾驶");
            }
        }.start();
    }

    public synchronized void speed(String name) {
        try {
            boolean stop = false;
            long time = 1000;
            while (!stop) {
                System.out.println(name + "加速:" + System.currentTimeMillis() + "ms");
//                Thread.sleep(time);
                this.wait();
                stop = true;
            }
            System.out.println(name + "加速完成" + System.currentTimeMillis() + "ms");
            slow(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void slow(String name) {
        try {
            boolean stop = false;
            long time = 5000;
            while (!stop) {
                System.out.println(name + "减速:" + System.currentTimeMillis() + "ms");
                Thread.sleep(time);
                stop = true;
            }
            System.out.println(name + "减速完成" + System.currentTimeMillis() + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
