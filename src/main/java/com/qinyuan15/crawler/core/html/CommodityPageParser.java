package com.qinyuan15.crawler.core.html;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface to parse commodity web page
 * Created by qinyuan on 15-1-2.
 */
public interface CommodityPageParser {
    void setHTML(String html);

    String getName();

    Double getOriginalPrice();

    Double getPrice();

    /**
     * @return A map that contains price history information,
     * key of map is date and value of map is price of that date
     */
    Map<Date, Double> getPriceHistory();

    List<String> getImageUrls();

    List<String> getDetailImagesUrls();

    Integer getSales();

    boolean isExpire();

    CommodityPageParser copy();
}
