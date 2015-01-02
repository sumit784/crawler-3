package com.qinyuan15.crawler.core.html;

import com.qinyuan15.crawler.dao.Commodity;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

/**
 * Class to parse page of http://s.etao.com
 * Created by qinyuan on 15-1-2.
 */
public class EtaoCommodityPageParser extends AbstractCommodityPageParser {

    @Override
    public Commodity getCommodity() {
        HtmlParser htmlParser = new HtmlParser(this.html);

        Commodity commodity = new Commodity();
        commodity.setName(getName(htmlParser));
        commodity.setOriginalPrice(getOriginalPrice(htmlParser));
        commodity.setPrice(getReadPrice(htmlParser));
        return commodity;
    }


    private String getName(HtmlParser htmlParser) {
        Elements nameElements = htmlParser.getElements("h1", "top-title");
        for (Element nameElement : nameElements) {
            String name = nameElement.text();
            if (StringUtils.hasText(name)) {
                return name;
            }
        }
        return null;
    }

    private Double getOriginalPrice(HtmlParser htmlParser) {
        Elements originalPriceElements = htmlParser.getElements("span", "original-price");
        for (Element originalPriceElement : originalPriceElements) {
            Double originalPrice = parseDouble(originalPriceElement.text());
            if (originalPrice != null) {
                return originalPrice;
            }
        }
        return null;
    }

    private Double getReadPrice(HtmlParser htmlParser) {
        Elements realPriceElements = htmlParser.getElements("span", "real-price-num");
        for (Element realPriceElement : realPriceElements) {
            Double realPrice = parseDouble(realPriceElement.text());
            if (realPrice != null) {
                return realPrice;
            }
        }
        return null;
    }

    private Double parseDouble(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        str = str.replaceAll("^\\D+", "");
        return StringUtils.hasText(str) ? Double.parseDouble(str) : null;
    }
}
