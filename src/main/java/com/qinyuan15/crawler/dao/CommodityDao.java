package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.IntegerUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao of Commodity
 * Created by qinyuan on 15-1-14.
 */
public class CommodityDao {

    public static Factory factory() {
        return new Factory();
    }

    public static class Factory {
        private Integer id;
        private boolean inLowPrice = false;
        private boolean orderByActive = false;
        private Integer categoryId;
        private Boolean active;

        public Factory setId(Integer id) {
            this.id = id;
            return this;
        }

        public Factory setActive(Boolean active) {
            this.active = active;
            return this;
        }

        public Factory setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        /**
         * if inLowPrice is set to true, create() only return
         * commodities that in lowest price during three month
         *
         * @param inLowPrice whether get commodities only in lowest price of three month
         */
        public Factory setInLowPrice(boolean inLowPrice) {
            this.inLowPrice = inLowPrice;
            return this;
        }

        public Factory orderByActive() {
            this.orderByActive = true;
            return this;
        }

        public List<Commodity> getInstances() {
            // build SQL query command
            String query = "FROM Commodity WHERE 1=1";

            if (IntegerUtils.isPositive(id)) {
                query += " AND id=" + id;
            }

            if (IntegerUtils.isPositive(categoryId)) {
                query += " AND categoryId=" + categoryId;
            }

            if (active != null) {
                query += " AND active=" + active;
            }

            if (this.orderByActive) {
                query += " ORDER BY active DESC";
            }

            // execute query
            @SuppressWarnings("unchecked")
            List<Commodity> commodities = HibernateUtil.getList(query);

            if (!inLowPrice) {
                return commodities;
            } else {
                List<Commodity> commoditiesInLowPrice = new ArrayList<Commodity>();
                String startTime = DateUtils.threeMonthAgo().toString();
                String endTime = DateUtils.now().toString();
                for (Commodity commodity : commodities) {
                    Integer commodityId = commodity.getId();
                    Double lowPrice = CommodityPriceDao.range(commodityId)
                            .setStartTime(startTime).setEndTime(endTime).getMin();
                    Double currentPrice = new CommodityPriceDao().getCurrentPrice(commodityId);
                    if (currentPrice - lowPrice <= 0.01) {
                        commoditiesInLowPrice.add(commodity);
                    }
                }
                return commoditiesInLowPrice;
            }
        }
    }

    public Commodity getInstance(int id) {
        List<Commodity> commodities = factory().setId(id).getInstances();
        return commodities.size() == 0 ? null : commodities.get(0);
    }

    public List<Commodity> getInstancesByShowId(String showId) {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Commodity WHERE showId=:showId";
        @SuppressWarnings("unchecked")
        List<Commodity> commodities = session.createQuery(hql).setString("showId", showId).list();
        HibernateUtil.commit(session);
        return commodities;
    }

    public void delete(int id) {
        HibernateUtil.delete(Commodity.class, id);
    }

    public void deactivate(int id) {
        Commodity commodity = HibernateUtil.get(Commodity.class, id);
        if (commodity != null) {
            commodity.setActive(false);
            HibernateUtil.update(commodity);
        }
    }

    public void activate(int id) {
        Commodity commodity = HibernateUtil.get(Commodity.class, id);
        if (commodity != null) {
            commodity.setActive(true);
            HibernateUtil.update(commodity);
        }
    }
}
