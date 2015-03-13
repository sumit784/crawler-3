package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;

import java.sql.Date;
import java.util.List;

/**
 * Dao of PriceRecord
 * Created by qinyuan on 15-1-13.
 */
public class PriceRecordDao {

    public static Factory factory() {
        return new Factory();
    }

    public static class Factory {
        private Integer commodityId;
        private Date recordTime;
        private String startTime;
        private String endTime;
        private String grabDate;

        public Factory setCommodityId(Integer commodityId) {
            this.commodityId = commodityId;
            return this;
        }

        public Factory setRecordTime(Date recordTime) {
            this.recordTime = recordTime;
            return this;
        }

        public Factory setStartTime(String startTime) {
            if (DateUtils.isDateOrDateTime(startTime)) {
                this.startTime = startTime;
            }
            return this;
        }

        public Factory setEndTime(String endTime) {
            if (DateUtils.isDateOrDateTime(endTime)) {
                this.endTime = endTime;
            }
            return this;
        }

        public Factory setGrabDate(String grabDate) {
            if (DateUtils.isDateOrDateTime(grabDate)) {
                this.grabDate = grabDate;
            }
            return this;
        }

        private String getHQL() {
            // build SQL query command
            String hql = "FROM PriceRecord WHERE 1=1";
            if (recordTime != null) {
                hql += " AND recordTime='" + recordTime + "'";
            }
            if (commodityId != null && commodityId > 0) {
                hql += " AND commodityId=" + commodityId;
            }
            if (startTime != null) {
                hql += " AND recordTime>='" + startTime + "'";
            }
            if (endTime != null) {
                hql += " AND recordTime<='" + endTime + "'";
            }
            if (grabDate != null) {
                hql += " AND DATE(grab_time)='" + grabDate + "'";
            }
            hql += " GROUP BY commodityId,recordTime";
            return hql;
        }

        @SuppressWarnings("unchecked")
        public List<PriceRecord> getInstances() {
            return HibernateUtil.getList(getHQL());
        }

        public PriceRecord getLastInstance() {
            String hql = getHQL() + " ORDER BY recordTime DESC";

            @SuppressWarnings("unchecked")
            List<PriceRecord> priceRecords = HibernateUtil.getList(hql, 0, 1);
            return priceRecords.size() == 0 ? null : priceRecords.get(0);
        }

        public PriceRecord getFirstInstance() {
            String hql = getHQL() + " ORDER BY recordTime ASC";

            @SuppressWarnings("unchecked")
            List<PriceRecord> priceRecords = HibernateUtil.getList(hql, 0, 1);
            return priceRecords.size() == 0 ? null : priceRecords.get(0);
        }

        public boolean hasInstance() {
            return getInstances().size() > 0;
        }
    }

    /**
     * Check whether today's price of a commodity is recorded
     *
     * @param commodityId id to commodity
     * @return if today's price of commodity is record, then return true
     */
    public boolean hasInstanceToday(int commodityId) {
        return factory().setCommodityId(commodityId)
                .setStartTime(DateUtils.todayStartTime())
                .setEndTime(DateUtils.todayEndTime())
                .hasInstance();
    }

    public PriceRecord getLastInstance(int commodityId){
        return factory().setCommodityId(commodityId).getLastInstance();
    }

    public PriceRecord getFirstInstance(int commodityId) {
        return factory().setCommodityId(commodityId).getFirstInstance();
    }
}
