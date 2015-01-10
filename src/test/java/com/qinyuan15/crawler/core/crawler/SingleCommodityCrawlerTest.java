package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.html.CommodityPageParser;
import com.qinyuan15.crawler.core.html.ComposableCommodityPageParser;
import com.qinyuan15.crawler.core.html.EtaoCommodityPageParser;
import com.qinyuan15.crawler.core.http.lib.TestCommodityPool;
import com.qinyuan15.crawler.core.http.lib.TestProxyPool;
import com.qinyuan15.crawler.core.http.proxy.ProxyPool;
import com.qinyuan15.crawler.dao.Commodity;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test SingleCommodityCrawler class
 * Created by qinyuan on 15-1-1.
 */
public class SingleCommodityCrawlerTest {
    @Test
    public void testSave() throws Exception {
        ProxyPool proxyPool = new TestProxyPool();
        SinglePriceHistoryCrawler crawler = new SinglePriceHistoryCrawler(
                mockComposableCommodityPageParser());
        //crawler.setProxyPool(proxyPool);
        Commodity commodity = new TestCommodityPool().next();
        crawler.save(commodity);
    }

    private ComposableCommodityPageParser mockComposableCommodityPageParser() {
        CommodityPageParser etaoCommodityPageParser = new EtaoCommodityPageParser();
        ComposableCommodityPageParser composableCommodityPageParser =
                new ComposableCommodityPageParser();

        Map<String, CommodityPageParser> parserMap = new HashMap<String, CommodityPageParser>();
        parserMap.put("etao", etaoCommodityPageParser);
        composableCommodityPageParser.setParsers(parserMap);
        return composableCommodityPageParser;
    }
}
