package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.lib.TestProxyPool;
import org.junit.Test;

/**
 * Created by qinyuan on 15-1-1.
 */
public class SingleCommodityCrawlerTest {
    @Test
    public void testSave() throws Exception {
        ProxyPool proxyPool = new TestProxyPool();
        SingleCommodityCrawler crawler = new SingleCommodityCrawler(proxyPool);
        crawler.save("www.baidu.com");
    }
}
