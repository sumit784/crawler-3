package com.qinyuan15.crawler.core.crawler;

import com.qinyuan15.crawler.core.crawler.ProxyCrawler;
import com.qinyuan15.crawler.core.crawler.ProxyCrawlerImpl;
import com.qinyuan15.crawler.core.html.PachongPageParser;
import com.qinyuan15.crawler.core.crawler.ProxyDownloader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test ProxyDownloader
 * Created by qinyuan on 14-12-30.
 */
public class ProxyDownloaderTest {
    private ProxyDownloader downloader = new ProxyDownloader();

    @Test
    public void testSave() throws Exception {
        ProxyCrawlerImpl crawler = new ProxyCrawlerImpl();
        crawler.setHost("http://pachong.org");
        crawler.setPageParser(new PachongPageParser());

        List<ProxyCrawler> crawlers = new ArrayList<ProxyCrawler>();
        crawlers.add(crawler);

        downloader.setCrawlers(crawlers);
        downloader.save();
    }
}
