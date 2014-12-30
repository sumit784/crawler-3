package com.qinyuan15.crawler.core.http;

import com.qinyuan15.crawler.dao.Proxy;

import java.util.List;

/**
 * Grab proxies information from certain page
 * Created by qinyuan on 14-12-29.
 */
public interface ProxyCrawler {
    List<Proxy> getProxies();
}
