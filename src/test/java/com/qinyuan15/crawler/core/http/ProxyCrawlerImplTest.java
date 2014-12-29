package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.html.PachongPageParser;
import org.junit.Test;

/**
 * Created by qinyuan on 14-12-29.
 */
public class ProxyCrawlerImplTest {
    @Test
    public void testGetProxies() throws Exception {
        ProxyCrawlerImpl crawler = new ProxyCrawlerImpl();
        crawler.setUrl("http://pachong.org");
        crawler.setPageParser(new PachongPageParser());
        crawler.getProxies();
    }
}
