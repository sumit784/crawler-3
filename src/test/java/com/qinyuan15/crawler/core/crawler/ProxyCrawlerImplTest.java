package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.crawler.ProxyCrawlerImpl;
import com.qinyuan15.crawler.core.html.PachongPageParser;
import com.qinyuan15.crawler.dao.Proxy;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Test ProxyCrawlerImpl
 * Created by qinyuan on 14-12-29.
 */
public class ProxyCrawlerImplTest {
    @Test
    public void testGetProxies() throws Exception {
        ProxyCrawlerImpl crawler = new ProxyCrawlerImpl();
        crawler.setHost("http://pachong.org");
        crawler.setPageParser(new PachongPageParser());
        List<Proxy> proxies = crawler.getProxies();
        assertThat(proxies).hasSize(50);
    }
}
