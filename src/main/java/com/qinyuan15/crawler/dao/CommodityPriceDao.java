package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;

/**
 * Query price of Commodity
 * Created by qinyuan on 15-1-22.
 */
public class CommodityPriceDao {

    public static PriceRange range(Integer commodityId) {
        return new PriceRange(commodityId);
    }

    /**
     * Class to query maximum and minimum price of certain commodity
     * during a period of time
     */
    public static class PriceRange {
        private Integer commodityId;
        private String startTime;
        private String endTime;

        private PriceRange(Integer commodityId) {
            this.commodityId = commodityId;
        }

        public PriceRange setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public PriceRange setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        private String getHQL() {
            String hql = "FROM PriceRecord WHERE commodityId=" + commodityId;
            if (startTime != null) {
                hql += " AND recordTime>='" + startTime + "'";
            }
            if (endTime != null) {
                hql += " AND recordTime<='" + endTime + "'";
            }
            return hql;
        }

        public Double getMax() {
            String hql = "SELECT MAX(price) " + getHQL();
            return (Double) HibernateUtil.getList(hql).get(0);
        }

        public Double getMin() {
            String hql = "SELECT MIN(price) " + getHQL();
            return (Double) HibernateUtil.getList(hql).get(0);
        }
    }

    public Double getMaxPrice(Integer commodityId) {
        return CommodityPriceDao.range(commodityId).getMax();
    }

    public Double getMinPriceInThreeMonth(Integer commodityId) {
        String startTime = DateUtils.threeMonthAgo().toString();
        return CommodityPriceDao.range(commodityId)
                .setStartTime(startTime)
                .getMin();
    }

    public Double getCurrentPrice(Integer commodityId) {
        PriceRecord priceRecord = new PriceRecordDao().getLastInstance(commodityId);
        return priceRecord == null ? null : priceRecord.getPrice();
    }
}
