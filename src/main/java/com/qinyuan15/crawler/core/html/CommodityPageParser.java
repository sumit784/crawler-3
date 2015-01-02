package com.qinyuan15.crawler.core.html;

import java.util.Map;

/**
 * Created by qinyuan on 15-1-2.
 */
public interface CommodityPageParser {
    void setHTML(String html);

    String getName();

    Double getOriginalPrice();

    Double getPrice();

    Map<String, Double> getPriceHistory();
}
