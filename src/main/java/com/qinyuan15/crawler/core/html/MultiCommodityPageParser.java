package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Commodity;

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
    public Commodity getCommodity() {
        if (this.html == null) {
            return null;
        }

        CommodityPageParser parser = null;
        for (Map.Entry<String, CommodityPageParser> entry : parsers.entrySet()) {
            parser = entry.getValue();
            if (this.html.startsWith(entry.getKey())) {
                break;
            }
        }

        if (parser != null) {
            parser.setHTML(this.html);
            return parser.getCommodity();
        } else {
            return null;
        }
    }
}
