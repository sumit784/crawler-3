package com.qinyuan15.crawler.core.html;

/**
 * Created by qinyuan on 14-12-29.
 */
abstract public class AbstractProxyPageParser implements ProxyPageParser {
    protected String html;

    @Override
    public void setHTML(String html) {
        this.html = html;
    }
}
