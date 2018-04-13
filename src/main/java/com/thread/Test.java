/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

/**
 *
 * @author ThinkPad
 */
public class Test {

    public static void main(String[] args) {
//设置开始结束时间
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTimeInMillis(System.currentTimeMillis());
        Date date = c.getTime();
        
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        long startDate = c.getTimeInMillis();
        String startDateStr = format.format(c.getTime());
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        long endDate = c.getTimeInMillis();
        String endDateStr = format.format(c.getTime());
        System.out.println(startDate + " : " + endDate);
    }
}
