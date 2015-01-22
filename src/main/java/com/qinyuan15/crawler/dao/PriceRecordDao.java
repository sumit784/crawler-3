package com.qinyuan15.crawler.dao;

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
            this.startTime = startTime;
            return this;
        }

        public Factory setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Factory setGrabDate(String grabDate) {
            this.grabDate = grabDate;
            return this;
        }

        // TODO this method should be revised to avoid SQL inject
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

        @SuppressWarnings("unchecked")
        public PriceRecord getLastInstance() {
            String hql = getHQL();
            hql += " ORDER BY recordTime DESC";
            List<PriceRecord> priceRecords = HibernateUtil.getList(hql, 0, 1);
            return priceRecords.size() == 0 ? null : priceRecords.get(0);
        }

        public boolean hasInstance() {
            return getInstances().size() > 0;
        }
    }
}
