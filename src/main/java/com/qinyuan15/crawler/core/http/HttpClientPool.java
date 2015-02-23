package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.proxy.ProxyPool;

/**
 * Pool class of HTTP client
 * Created by qinyuan on 15-2-23.
 */
public class HttpClientPool {
    private ProxyPool proxyPool;

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public HttpClientWrapper next() {
        HttpClientWrapper clientWrapper = new HttpClientWrapper();
        if (proxyPool != null) {
            clientWrapper.setProxy(proxyPool.next());
        }
        return clientWrapper;
    }
}
