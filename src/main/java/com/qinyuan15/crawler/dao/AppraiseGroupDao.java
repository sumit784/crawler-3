package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

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

    public void clear(int commodityId, boolean positive) {
        HibernateUtil.delete(AppraiseGroup.class,
                "commodityId=" + commodityId + " AND positive=" + positive);
    }

    public void clearAndSave(int commodityId, String[] contents, boolean positive) {
        this.clear(commodityId, positive);
        this.save(commodityId, contents, positive);
    }

    public void save(int commodityId, String[] contents, boolean positive) {
        if (contents == null || commodityId <= 0) {
            return;
        }

        Session session = HibernateUtil.getSession();
        for (String content : contents) {
            AppraiseGroup appraiseGroup = new AppraiseGroup();
            appraiseGroup.setCommodityId(commodityId);
            appraiseGroup.setContent(content);
            appraiseGroup.setPositive(positive);
            session.save(appraiseGroup);
        }
        HibernateUtil.commit(session);
    }
}
