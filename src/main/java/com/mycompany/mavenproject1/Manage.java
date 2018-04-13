/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author ThinkPad
 */
public class Manage extends Thread {

    @Override
    public void run() {
        System.out.println("Manage start");
        int index = 0;
//        while (true) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            index++;
//            System.out.println("Manage 执行次数：" + index);
//            System.out.println("Manage list size：" + ThreadTest.threadList.size());
//            Iterator<Map.Entry<String, ThreadStatus>> entries = ThreadTest.threadList.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, ThreadStatus> entrySet = entries.next();
//                String key = entrySet.getKey();
//                ThreadStatus ts = entrySet.getValue();
//                long time = System.currentTimeMillis() - ts.getStartTime();
//                System.out.println("time:" + time);
//                if (time > 1500) {
//                    entries.remove();
//                    try {
//                        ts.getThread().interrupt();
//                        Constructor c = Class.forName("com.mycompany.mavenproject1.Job").getConstructor(String.class);
//                        ts = null;
//                        Job thread = (Job) c.newInstance("Job" + System.currentTimeMillis());
////                        Thread thread = new Thread(new Job("Job" + System.currentTimeMillis()));
//                        thread.start();
//                        ThreadStatus tStatus = new ThreadStatus();
//                        tStatus.setStartTime(System.currentTimeMillis());
//                        tStatus.setThread(thread);
//                        ThreadTest.threadList.put("Job", tStatus);
//                    } catch (Exception ex) {
//                        Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, ex);
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }
    }
}
