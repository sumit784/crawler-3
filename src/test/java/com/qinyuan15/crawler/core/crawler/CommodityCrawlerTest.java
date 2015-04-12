package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.html.ComposableCommodityPageParserTest;
import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import org.junit.Test;

/**
 * Test PriceHisotryCrawler
 * Created by qinyuan on 15-1-11.
 */
public class CommodityCrawlerTest {

    @Test
    public void test() throws Exception {
        CommodityCrawler crawler = new CommodityCrawler();
        crawler.setCommodityPool(new TestCommodityPool());
        crawler.setCommodityPageParser(
                ComposableCommodityPageParserTest.mockComposableCommodityPageParser());
        crawler.init();
        Thread.sleep(20000); // run 10 minutes
    }
}
