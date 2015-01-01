package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.Proxy;
import com.qinyuan15.crawler.dao.ProxyDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Grub Proxy information then save them to database
 * Created by qinyuan on 14-12-29.
 */
public class ProxyDownloader {
    private List<ProxyCrawler> crawlers;

    public void setCrawlers(List<ProxyCrawler> crawlers) {
        this.crawlers = crawlers;
    }

    public void save() {
        List<Proxy> proxies = new ArrayList<Proxy>();
        for (ProxyCrawler crawler : crawlers) {
            proxies.addAll(crawler.getProxies());
        }

        int largeInt = 10000000;
        for (Proxy proxy : proxies) {
            proxy.setSpeed(largeInt);
        }

        new ProxyDao().save(proxies);
    }
}
