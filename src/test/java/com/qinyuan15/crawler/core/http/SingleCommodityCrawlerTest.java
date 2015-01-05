package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.html.CommodityPageParser;
import com.qinyuan15.crawler.core.html.EtaoCommodityPageParser;
import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import com.qinyuan15.crawler.core.http.lib.TestProxyPool;
import com.qinyuan15.crawler.dao.Commodity;
import org.junit.Test;

/**
 * Created by qinyuan on 15-1-1.
 */
public class SingleCommodityCrawlerTest {
    @Test
    public void testSave() throws Exception {
        ProxyPool proxyPool = new TestProxyPool();
        CommodityPageParser pageParser = new EtaoCommodityPageParser();
        SinglePriceHistoryCrawler crawler = new SinglePriceHistoryCrawler(pageParser);
        //crawler.setProxyPool(proxyPool);
        Commodity commodity = new TestCommodityPool().next();
        crawler.save(commodity);
    }
}
