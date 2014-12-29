package com.qinyuan15.crawler.core.http;

import java.util.List;

/**
 * Wrap Http Proxy
 * Created by qinyuan on 14-12-24.
 */
public interface HttpProxyPool {

    List<HttpProxy> getAll();

    HttpProxy next();

    int size();
}
