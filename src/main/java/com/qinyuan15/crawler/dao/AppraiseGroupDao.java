package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao of AppraiseGroup
 * Created by qinyuan on 15-2-24.
 */
public class AppraiseGroupDao {
    @SuppressWarnings("unchecked")
    public List<AppraiseGroup> getInstancesByCommodityId(int commodityId) {
        return HibernateUtil.getList("AppraiseGroup WHERE commodityId=" + commodityId);
    }

    @SuppressWarnings("unchecked")
    public List<AppraiseGroup> getInstancesByCommodityId(int commodityId, boolean positive) {
        return HibernateUtil.getList("AppraiseGroup WHERE commodityId=" + commodityId +
                " AND positive=" + positive);
    }
}
