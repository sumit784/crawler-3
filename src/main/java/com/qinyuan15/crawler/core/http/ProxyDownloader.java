package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.ProxyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinyuan on 14-12-29.
 */
public class ProxyDownloader {
    private List<ProxyCrawler> crawlers;

    public void setCrawlers(List<ProxyCrawler> crawlers) {
        this.crawlers = crawlers;
    }

    public void save() {
        List<HttpProxy> proxies = new ArrayList<HttpProxy>();
        for (ProxyCrawler crawler : crawlers) {
            proxies.addAll(crawler.getProxies());
        }
        ProxyDao dao = new ProxyDao();
        dao.add(proxies);
    }
}
