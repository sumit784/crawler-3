package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.html.ComposableCommodityPageParserTest;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import com.qinyuan15.crawler.dao.Commodity;
import org.junit.Test;

/**
 * Test SingleCommodityCrawler
 * Created by qinyuan on 15-1-11.
 */
public class SingleCommodityCrawlerTest {
    @Test
    public void test() throws Exception {
        SingleCommodityCrawler crawler = new SingleCommodityCrawler(
                ComposableCommodityPageParserTest.mockComposableCommodityPageParser(),
                /*ImageDownloaderTest.mockImageDownloader(),*/
                new HttpClientPool()
        );
        Commodity commodity = new TestCommodityPool().next();
        crawler.save(commodity);
    }
}
