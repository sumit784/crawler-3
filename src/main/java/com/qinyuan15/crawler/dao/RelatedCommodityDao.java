package com.qinyuan15.crawler.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao to deal with related commodity
 * Created by qinyuan on 15-3-15.
 */
public class RelatedCommodityDao {
    public List<Commodity> getInstances(Commodity commodity) {
        if (commodity == null || commodity.getCategoryId() == null) {
            return new ArrayList<>();
        }

        return CommodityDao.factory().setCategoryId(commodity.getCategoryId()).getInstances();
    }
}
