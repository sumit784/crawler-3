package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Dao of PriceRecord
 * Created by qinyuan on 15-1-13.
 */
public class CommodityDao {

    public static Factory factory() {
        return new Factory();
    }

    @SuppressWarnings("unchecked")
    public static class Factory {
        private Integer id;

        public Factory setId(Integer id) {
            this.id = id;
            return this;
        }

        public List<Commodity> getInstances() {
            String query = "FROM Commodity WHERE 1=1";
            if (id != null && id > 0) {
                query += " AND id=" + id;
            }

            return HibernateUtil.getList(query);
        }
    }
}
