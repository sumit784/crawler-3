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

        List<Commodity> commodities = CommodityDao.factory().setCategoryId(commodity.getCategoryId()).getInstances();
        for (int i = 0; i < commodities.size(); i++) {
            if (commodities.get(i).getId().equals(commodity.getId())) {
                commodities.remove(i);
                break;
            }
        }
        return commodities;
    }
}