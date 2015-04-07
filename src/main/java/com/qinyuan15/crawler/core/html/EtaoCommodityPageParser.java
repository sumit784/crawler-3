package com.qinyuan15.crawler.core.html;

import com.google.common.collect.Lists;
import com.qinyuan15.crawler.utils.DateUtils;
import com.qinyuan15.crawler.core.http.HttpClientPool;
import com.qinyuan15.crawler.core.http.HttpClientWrapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to parse page of http://s.etao.com
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParser extends AbstractCommodityPageParser {

    private JavaScriptParser jsParser = new JavaScriptParser();
    private HttpClientPool httpClientPool;

    public void setHttpClientPool(HttpClientPool httpClientPool) {
        this.httpClientPool = httpClientPool;
    }

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
    public Integer getSales() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Element productInfoDiv = htmlParser.getElement("div", "product-info");
        if (productInfoDiv == null) {
            return null;
        }

        Element ulElement = HtmlParser.getSubElement(productInfoDiv, "ul", "meta");
        if (ulElement == null) {
            return null;
        }

        Elements liElements = ulElement.getElementsByTag("li");
        char htmlSpace = 160;
        String regex = String.format("销[\\s%c]*量\\d+", htmlSpace);
        Pattern pattern = Pattern.compile(regex);
        for (Element liElement : liElements) {
            String text = liElement.text().trim();
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return NumberUtils.toInt(matcher.group().replaceAll("\\D", ""));
            }
        }
        return null;
    }

    @Override
    public boolean isExpire() {
        return this.html.contains("您所访问的页面已经被删除") ||
                this.html.contains("您访问的内容不存在，您可尝试重新访问!");
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

    @Override
    public List<String> getDetailImagesUrls() {
        HtmlParser htmlParser = new HtmlParser(this.html);
        Element productDetailDiv = htmlParser.getElements("div", "product-detail").first();
        if (productDetailDiv == null) {
            return null;
        }

        String dataUrl = productDetailDiv.attr("data-url");
        if (dataUrl == null) {
            return null;
        }

        HttpClientWrapper client = this.httpClientPool == null ?
                new HttpClientWrapper() : this.httpClientPool.next();
        String dataUrlContent = client.getContent(dataUrl);
        return this.parseDetailImageUrls(dataUrlContent);
    }

    private List<String> parseDetailImageUrls(String content) {
        content = content.trim().replace("var desc='", "").replace("';", "");
        HtmlParser parser = new HtmlParser(content);
        List<String> urls = new ArrayList<String>();
        for (Element element : parser.getElements("img")) {
            if (!element.hasClass("desc_anchor")) {
                urls.add(element.attr("src"));
            }
        }
        return urls;
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
