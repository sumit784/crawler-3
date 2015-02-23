package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.core.http.proxy.ProxyPool;
import com.qinyuan15.crawler.core.http.proxy.ProxySpeedRecorder;

/**
 * Pool class of HTTP client
 * Created by qinyuan on 15-2-23.
 */
public class HttpClientPool {
    private ProxyPool proxyPool;
    private ProxySpeedRecorder proxySpeedRecorder;

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void setProxySpeedRecorder(ProxySpeedRecorder proxySpeedRecorder) {
        this.proxySpeedRecorder = proxySpeedRecorder;
    }

    public HttpClientWrapper next() {
        HttpClientWrapper clientWrapper = new HttpClientWrapper();
        if (this.proxyPool != null) {
            clientWrapper.setProxy(this.proxyPool.next());
        }
        clientWrapper.setProxySpeedRecorder(this.proxySpeedRecorder);
        return clientWrapper;
    }
}
