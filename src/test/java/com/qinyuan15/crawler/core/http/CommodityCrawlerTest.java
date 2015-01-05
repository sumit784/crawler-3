package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import org.junit.Test;

/**
 * Created by qinyuan on 15-1-2.
 */
public class CommodityCrawlerTest {
    @Test
    public void testInit() throws Exception {
        PriceHistoryCrawler crawler = new PriceHistoryCrawler();
        crawler.setCommodityPool(new TestCommodityPool());
        crawler.setInterval(1);
        crawler.init();
        Thread.sleep(10000);
    }
}
