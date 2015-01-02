package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.Commodity;
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
    private ProxyPool proxyPool;
    private CommodityPool commodityPool;

    public void init() {
        for (int i = 0; i < this.threadSize; i++) {
            new CrawlThread().start();
        }
    }

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void setCommodityPool(CommodityPool commodityPool) {
        this.commodityPool = commodityPool;
    }

    public void setThreadSize(int threadSize) {
        this.threadSize = threadSize;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    private class CrawlThread extends Thread {
        private SingleCommodityCrawler singleCommodityCrawler;

        public CrawlThread() {
            this.singleCommodityCrawler = new SingleCommodityCrawler();
            this.singleCommodityCrawler.setProxyPool(proxyPool);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(interval * 1000);
                    if (commodityPool != null) {
                        Commodity commodity = commodityPool.next();
                        this.singleCommodityCrawler.save(commodity.getUrl());
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
