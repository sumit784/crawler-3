package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import com.qinyuan15.crawler.core.http.lib.TestProxyPool;
import org.junit.Test;

/**
 * Created by qinyuan on 15-1-1.
 */
public class SingleCommodityCrawlerTest {
    @Test
    public void testSave() throws Exception {
        ProxyPool proxyPool = new TestProxyPool();
        SingleCommodityCrawler crawler = new SingleCommodityCrawler();
        //crawler.setProxyPool(proxyPool);
        String url = new TestCommodityPool().next().getUrl();
        crawler.save(url);
    }
}
