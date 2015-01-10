package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.core.DateUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to parse page of http://s.etao.com
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParser extends AbstractCommodityPageParser {
    public String getName() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Elements nameElements = htmlParser.getElements("h1", "top-title");
        for (Element nameElement : nameElements) {
            String name = nameElement.text();
            if (StringUtils.hasText(name)) {
                return name;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getOriginalPrice() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Elements originalPriceElements = htmlParser.getElements("span", "original-price");
        for (Element originalPriceElement : originalPriceElements) {
            Double originalPrice = parseDouble(originalPriceElement.text());
            if (originalPrice != null) {
                return originalPrice;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getPrice() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Elements realPriceElements = htmlParser.getElements("span", "real-price-num");
        for (Element realPriceElement : realPriceElements) {
            Double realPrice = parseDouble(realPriceElement.text());
            if (realPrice != null) {
                return realPrice;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Date, Double> getPriceHistory() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        JavaScriptParser scriptParser = new JavaScriptParser();
        Elements productElements = htmlParser.getElements("div", "product");
        for (Element productElement : productElements) {
            Elements jsElements = productElement.getElementsByTag("script");
            if (jsElements.size() == 0) {
                continue;
            }

            Element jsElement = jsElements.get(0);
            Object priceTrend = scriptParser.eval(jsElement.html() + ";data_pricetrend");
            if (priceTrend instanceof NativeObject) {
                NativeObject priceTrendObj = (NativeObject) priceTrend;
                Object points = priceTrendObj.get("points");
                if (points instanceof NativeArray) {
                    return getPriceHistory((NativeArray) points);
                }
            }
        }
        return null;
    }

    private Map<Date, Double> getPriceHistory(NativeArray array) {
        Map<Date, Double> map = new TreeMap<Date, Double>();
        for (Object element : array) {
            if (element instanceof NativeArray) {
                NativeArray arrayElement = (NativeArray) element;
                Object date = arrayElement.get(0);
                Object price = arrayElement.get(1);
                if (date instanceof String) {
                    if (price instanceof Double) {
                        map.put(DateUtils.newDate((String) date), (Double) price);
                    } else if (price instanceof Integer) {
                        map.put(DateUtils.newDate((String) date), ((Integer) price).doubleValue());
                    }
                }
            }
        }
        return map;
    }

    private Double parseDouble(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        str = str.replaceAll("^\\D+", "");
        return StringUtils.hasText(str) ? Double.parseDouble(str) : null;
    }
}
