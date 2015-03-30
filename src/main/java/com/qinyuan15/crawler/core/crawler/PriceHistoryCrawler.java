package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.commodity.CommodityPool;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.image.ImageDownloader;
import com.qinyuan15.crawler.dao.Commodity;
import com.qinyuan15.crawler.dao.PriceRecordDao;
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
    private HttpClientPool httpClientPool;
    private CommodityPool commodityPool;
    private ComposableCommodityPageParser commodityPageParser;
    private ImageDownloader imageDownloader;
    private int startHour = -1;
    private int endHour = -1;

    public void init() {
        for (int i = 0; i < this.threadSize; i++) {
            LOGGER.info("start PriceHistory crawl thread " + i);
            new CrawlThread().start();
            try {
                // wait two seconds before starting next single crawler
                Thread.sleep(2000);
            } catch (Exception e) {
                LOGGER.warn("exception in init(): {}", e);
            }
        }
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setHttpClientPool(HttpClientPool httpClientPool) {
        this.httpClientPool = httpClientPool;
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

    public void setImageDownloader(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    private boolean inCrawlTime() {
        if (this.startHour < 0 || this.endHour < 0) {
            return true;
        }

        int currentHour = DateUtils.currentHour();
        if (this.startHour < this.endHour) {
            return currentHour >= this.startHour && currentHour < this.endHour;
        } else {
            return currentHour >= this.startHour || currentHour < this.endHour;
        }
    }

    private class CrawlThread extends Thread {
        private SinglePriceHistoryCrawler singleCommodityCrawler;

        public CrawlThread() {
            this.singleCommodityCrawler = new SinglePriceHistoryCrawler(
                    commodityPageParser, imageDownloader, httpClientPool);
        }

        @Override
        public void run() {
            if (commodityPool == null) {
                LOGGER.info("commodity pool is null, no commodity history to grub.");
                return;
            }

            PriceRecordDao dao = new PriceRecordDao();

            while (true) {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    LOGGER.error("Thread fail to sleep: {}", e);
                }

                if (inCrawlTime()) {
                    Commodity commodity = commodityPool.next();
                    try {
                        if (commodity == null) {
                            commodityPool.reset();
                        } else {
                            if (dao.hasInstanceToday(commodity.getId())) {
                                LOGGER.info("Today's price of commodity {} already save, just skip it",
                                        commodity.getName());
                            } else {
                                this.singleCommodityCrawler.save(commodity);
                            }
                        }
                    } catch (Exception e) {
                        String commodityInfo = commodity == null ?
                                null : commodity.getName() + "(" + commodity.getUrl() + ")";
                        LOGGER.error("fail to grub commodity '{}': {}",
                                commodityInfo, e);
                    }
                }
            }
        }
    }
}
