package com.qinyuan15.crawler.core.html;

import java.sql.Date;
import java.util.Map;

/**
 * This class combine several CommodityPageParer,
 * when parsing certain web page, this class will select suitable
 * CommodityPageParser according to the url prefix of web url
 * Created by qinyuan on 15-1-2.
 */
public class ComposableCommodityPageParser extends AbstractCommodityPageParser {
    private String webUrl;
    private Map<String, CommodityPageParser> parsers;

    /**
     * Set CommodityPageParser(s).
     * key is the url pattern parser should parse, and value is the parser
     *
     * @param parsers CommodityPageParser(s)
     */
    public void setParsers(Map<String, CommodityPageParser> parsers) {
        this.parsers = parsers;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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
    public Map<Date, Double> getPriceHistory() {
        return getSuitableParser().getPriceHistory();
    }

    private CommodityPageParser getSuitableParser() {
        if (this.html == null) {
            return null;
        }

        /*
         * Search the parser whose url pattern starts with this.webUrl,
         * if found, return it
         */
        for (Map.Entry<String, CommodityPageParser> entry : parsers.entrySet()) {
            if (this.webUrl.startsWith(entry.getKey())) {
                CommodityPageParser parser = entry.getValue();
                parser.setHTML(html);
                return parser;
            }
        }

        /*
         * If program reach here, there must be no parser whose url pattern
         * starts with this.webUrl. So search the parser whose url pattern
         * contains this.webUrl.
         */
        for (Map.Entry<String, CommodityPageParser> entry : parsers.entrySet()) {
            if (this.webUrl.contains(entry.getKey())) {
                CommodityPageParser parser = entry.getValue();
                parser.setHTML(html);
                return parser;
            }
        }

        return null;
    }
}