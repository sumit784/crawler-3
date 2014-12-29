package com.qinyuan15.crawler.core.http;

/**
 * Wrap Http Proxy
 * Created by qinyuan on 14-12-24.
 */
public class HttpProxy {

    private String host;
    private int port;

    public HttpProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
