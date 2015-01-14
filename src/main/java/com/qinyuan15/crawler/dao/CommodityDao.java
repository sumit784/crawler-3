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

    public static class Factory {
        private Integer id;

        public Factory setId(Integer id) {
            this.id = id;
            return this;
        }

        public List<Commodity> getInstances() {
            // build SQL query command
            String query = "FROM Commodity WHERE 1=1";

            if (id != null && id > 0) {
                query += " AND id=" + id;
            }

            // execute query
            Session session = HibernateUtil.getSession();
            @SuppressWarnings("unchecked")
            List<Commodity> commodities = session.createQuery(query).list();
            session.close();
            return commodities;
        }
    }
}
