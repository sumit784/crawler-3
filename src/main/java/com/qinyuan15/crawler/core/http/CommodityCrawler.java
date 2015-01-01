package com.qinyuan15.crawler.core.http;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by qinyuan on 15-1-1.
 */
public class CommodityCrawler {

    private int threadSize;
    private int interval;

    public CommodityCrawler() {
        System.out.println("constructor");
        /*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("HelloWorld");
            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(runnable, 1, 10, TimeUnit.SECONDS);
        */
    }

    public void init() {
        System.out.println("init");
    }

    public void setThreadSize() {
        System.out.println("set thread size");
    }

    public void setInterval() {
        System.out.println("set interval");
    }
}
