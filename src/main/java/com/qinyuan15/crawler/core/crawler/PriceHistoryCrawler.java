package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.commodity.CommodityPool;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.proxy.ProxyPool;
import com.qinyuan15.crawler.dao.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Grub price of commodity
 * Created by qinyuan on 15-1-1.
 */
public class PriceHistoryCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(PriceHistoryCrawler.class);
    public final static int DEFAULT_THREAD_SIZE = 10;
    public final static int DEFAULT_INTERVAL = 10;

    private int threadSize = DEFAULT_THREAD_SIZE;
    private int interval = DEFAULT_INTERVAL;
    private ProxyPool proxyPool;
    private CommodityPool commodityPool;
    private ComposableCommodityPageParser commodityPageParser;

    public void init() {
        for (int i = 0; i < this.threadSize; i++) {
            new CrawlThread().start();
        }
    }

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void setCommodityPageParser(ComposableCommodityPageParser commodityPageParser) {
        this.commodityPageParser = commodityPageParser;
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
        private SinglePriceHistoryCrawler singleCommodityCrawler;

        public CrawlThread() {
            this.singleCommodityCrawler = new SinglePriceHistoryCrawler(commodityPageParser);
            this.singleCommodityCrawler.setProxyPool(proxyPool);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(interval * 1000);
                    if (commodityPool != null) {
                        Commodity commodity = commodityPool.next();
                        this.singleCommodityCrawler.save(commodity);
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
