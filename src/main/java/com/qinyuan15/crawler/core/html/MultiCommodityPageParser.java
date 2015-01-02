package com.qinyuan15.crawler.core.html;

import java.util.Map;

/**
 * Created by qinyuan on 15-1-2.
 */
public class MultiCommodityPageParser extends AbstractCommodityPageParser {
    // key is url prefix, value is CommodityPageParser instance
    private Map<String, CommodityPageParser> parsers;

    public void setParsers(Map<String, CommodityPageParser> parsers) {
        this.parsers = parsers;
    }


    @Override
    public String getName() {
        return getSuitableParser().getName();
    }

    @Override
    public Double getOriginalPrice() {
        return getSuitableParser().getOriginalPrice();
    }

    @Override
    public Double getPrice() {
        return getSuitableParser().getPrice();
    }

    @Override
    public Map<String, Double> getPriceHistory() {
        return getSuitableParser().getPriceHistory();
    }

    private CommodityPageParser getSuitableParser() {
        if (this.html == null) {
            return null;
        }

        CommodityPageParser parser = null;
        for (Map.Entry<String, CommodityPageParser> entry : parsers.entrySet()) {
            parser = entry.getValue();
            parser.setHTML(html);
            if (this.html.startsWith(entry.getKey())) {
                return parser;
            }
        }
        return parser;
    }
}
