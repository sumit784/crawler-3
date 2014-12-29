package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.core.http.HttpProxy;

import java.util.List;

/**
 * Parse Proxy information from proxy page
 * Created by qinyuan on 14-12-29.
 */
public interface ProxyPageParser {
    void setHTML(String html);
    List<HttpProxy> getProxies();
    List<String> getSubLinks();
}
