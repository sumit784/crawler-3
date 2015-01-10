package com.qinyuan15.crawler.core.html;

/**
 * Implement some methods of CommodityPageParser
 * Created by qinyuan on 15-1-2.
 */
abstract public class AbstractCommodityPageParser implements CommodityPageParser {
    protected String html;

    @Override
    public void setHTML(String html) {
        this.html = html;
    }
}
