package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.Proxy;

import java.util.List;

/**
 * Pool of Http Proxy
 * Created by qinyuan on 14-12-24.
 */
public interface ProxyPool {

    Proxy next();

    void updateSpeed(Proxy proxy);

    int size();
}
