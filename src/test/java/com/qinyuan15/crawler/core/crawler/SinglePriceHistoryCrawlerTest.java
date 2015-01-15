package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.html.ComposableCommodityPageParserTest;
import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import com.qinyuan15.crawler.core.image.ImageDownloaderTest;
import com.qinyuan15.crawler.dao.Commodity;
import org.junit.Test;

/**
 * Test SinglePriceHistoryCrawler
 * Created by qinyuan on 15-1-11.
 */
public class SinglePriceHistoryCrawlerTest {
    @Test
    public void test() throws Exception {
        SinglePriceHistoryCrawler crawler = new SinglePriceHistoryCrawler(
                ComposableCommodityPageParserTest.mockComposableCommodityPageParser(),
                ImageDownloaderTest.mockImageDownloader()
        );
        Commodity commodity = new TestCommodityPool().next();
        crawler.save(commodity);
    }
}
