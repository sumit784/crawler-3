package com.qinyuan15.crawler.core.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qinyuan on 15-1-1.
 */
public class CommodityCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityCrawler.class);
    public final static int DEFAULT_THREAD_SIZE = 10;
    public final static int DEFAULT_INTERVAL = 10;

    private int threadSize = DEFAULT_THREAD_SIZE;
    private int interval = DEFAULT_INTERVAL;
    private int count = 0;

    public void init() {
        for (int i = 0; i < this.threadSize; i++) {
            new CrawlThread().start();
        }
    }

    public void setThreadSize(int threadSize) {
        this.threadSize = threadSize;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    private class CrawlThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(interval * 1000);
                    LOGGER.info("save commodity {}", count++);
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
