package com.qinyuan15.crawler.core.html;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.core.DateUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to parse page of http://s.etao.com
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParser extends AbstractCommodityPageParser {

    private JavaScriptParser jsParser = new JavaScriptParser();

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
    @SuppressWarnings("unchecked")
    @Override
    public Map<Date, Double> getPriceHistory() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Elements productElements = htmlParser.getElements("div", "product");
        for (Element productElement : productElements) {
            Elements jsElements = productElement.getElementsByTag("script");
            if (jsElements.size() == 0) {
                continue;
            }

            Element jsElement = jsElements.get(0);
            Object priceTrend = jsParser.eval(jsElement.html() + ";data_pricetrend");
            if (jsParser.isObject(priceTrend)) {
                Object points = jsParser.parseObject(priceTrend, "points");
                if (jsParser.isArray(points)) {
                    return getPriceHistory((Iterable<Object>) points);
                }
            }
        }
        return null;
    }

    @Override
    public List<String> getImageUrls() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Element productPictureDiv = htmlParser.getElement("J_product-pic");
        if (productPictureDiv == null) {
            return null;
        }

        String dataConfig = productPictureDiv.attr("data-config");
        if (StringUtils.hasText(dataConfig)) {
            String imageString = dataConfig.replaceAll("^[^\\[]*\\['", "").replaceAll("'\\][^\\]]*$", "");
            return Lists.newArrayList(imageString.split("','"));
        } else {
            return null;
        }
    }

    private Map<Date, Double> getPriceHistory(Iterable<Object> array) {
        Map<Date, Double> map = new TreeMap<Date, Double>();
        for (Object element : array) {
            if (jsParser.isArray(element)) {
                Object date = jsParser.parseArray(element, 0);
                Object price = jsParser.parseArray(element, 1);
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
