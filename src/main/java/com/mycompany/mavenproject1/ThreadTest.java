/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ThinkPad
 */
public class ThreadTest {

    public static Map<String, ThreadStatus> threadList = new HashMap();

    public static void main(String[] args) {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        schedule.schedule(new Manage(), 10l, TimeUnit.SECONDS);
        schedule.shutdown();
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.println(" begin to do something at:" + sdf.format(new Date()));
//        schedule.scheduleAtFixedRate(new Job(), 1, 2, TimeUnit.SECONDS);
//        new Thread(new Manage()).start();
//        Thread t = new Thread(new Job("Job"));
//        t.start();
//        ThreadStatus ts = new ThreadStatus();
//        ts.setStartTime(System.currentTimeMillis());
//        ts.setThread(t);
//        ThreadTest.threadList.put("Job", ts);
    }
}
