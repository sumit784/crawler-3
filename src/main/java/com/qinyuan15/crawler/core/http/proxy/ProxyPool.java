package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.dao.Proxy;

/**
 * Pool of Http Proxy
 * Created by qinyuan on 14-12-24.
 */
public interface ProxyPool {

    Proxy next();

    int size();
}
