package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qinyuan on 15-1-1.
 */
class SingleCommodityCrawler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SingleCommodityCrawler.class);

    private ProxyPool proxyPool;

    public SingleCommodityCrawler(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void save(String url) {
        Proxy proxy = proxyPool.next();
        //LOGGER.info("download {} with proxy {}", url, proxy);
    }
}
