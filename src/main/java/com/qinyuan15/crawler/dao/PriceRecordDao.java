package com.qinyuan15.crawler.dao;

import org.hibernate.Session;

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

        @SuppressWarnings("unchecked")
        public List<PriceRecord> getInstances() {
            // build SQL query command
            String query = "FROM PriceRecord WHERE 1=1";
            if (recordTime != null) {
                query += " AND recordTime='" + recordTime + "'";
            }
            if (commodityId != null && commodityId > 0) {
                query += " AND commodityId=" + commodityId;
            }
            if (startTime != null) {
                query += " AND recordTime>='" + startTime + "'";
            }
            if (endTime != null) {
                query += " AND recordTime<='" + endTime + "'";
            }
            if (grabDate != null) {
                query += " AND DATE(grab_time)='" + grabDate + "'";
            }

            return HibernateUtil.getList(query);
        }

        public boolean hasInstance() {
            return getInstances().size() > 0;
        }
    }
}
