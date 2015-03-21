package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Dao of HotSearchWord
 * Created by qinyuan on 15-2-28.
 */
public class HotSearchWordDao {
    /*
    @SuppressWarnings("unchecked")
    public List<HotSearchWord> getInstances(Integer categoryId, int size) {
        String hql = "FROM HotSearchWord";
        if (IntegerUtils.isPositive(categoryId)) {
            hql += " WHERE categoryId=" + categoryId + " ORDER BY searchCount DESC,lastTime DESC";
        } else {
            hql += " ORDER BY categoryId ASC,searchCount DESC,lastTime DESC";
        }
        return HibernateUtil.getList(hql, 0, size);
    }
    */


    public List<HotSearchWord> getInstances(Integer categoryId) {
        return HibernateUtil.getList(HotSearchWord.class, "categoryId=" + categoryId + " ORDER BY ranking ASC");
    }

    public void clear(int categoryId) {
        HibernateUtil.delete(HotSearchWord.class, "categoryId=" + categoryId);
    }

}
