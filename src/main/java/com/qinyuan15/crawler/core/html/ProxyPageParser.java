package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Proxy;

import java.util.List;

/**
 * Parse Proxy information from proxy page
 * Created by qinyuan on 14-12-29.
 */
public interface ProxyPageParser {
    void setHTML(String html);
    List<Proxy> getProxies();
    List<String> getSubLinks();
}
