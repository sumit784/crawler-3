package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Commodity;

/**
 * Created by qinyuan on 15-1-2.
 */
public interface CommodityPageParser {
    void setHTML(String html);

    Commodity getCommodity();
}
