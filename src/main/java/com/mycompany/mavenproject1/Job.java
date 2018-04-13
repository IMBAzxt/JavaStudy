/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author ThinkPad
 */
public class Job extends Thread {
    private String jobName;
    private ThreadStatus ts;

    public Job(String jobName) {
        this.jobName = jobName;
//        ts = new ThreadStatus();
//        ts.setIsFinish(false);
//        ts.setStartTime(System.currentTimeMillis());
//        ts.setThread(this);
//        ThreadTest.threadList.put(jobName, ts);
    }

    @Override
    public void run() {
        int index = 0;
        boolean interrupt = false;
        while (!interrupt) {
            index++;
            System.out.println(this.jobName + " 执行次数：" + index);
            try {
                if (index > 2) {
                    Thread.sleep(2000);
                }
                Thread.sleep(1000);
                Iterator<Map.Entry<String, ThreadStatus>> entries = ThreadTest.threadList.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, ThreadStatus> entrySet = entries.next();
                    String key = entrySet.getKey();
                    ThreadStatus ts = entrySet.getValue();
                    if (ts.getThread().equals(Thread.currentThread())) {
                        ts.setStartTime(System.currentTimeMillis());
                    }
                }
            } catch (Exception ex) {
                interrupt = true;
            }
        }
    }
}
