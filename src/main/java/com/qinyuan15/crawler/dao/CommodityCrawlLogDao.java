package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.utils.DateUtils;
import com.qinyuan15.crawler.utils.IntegerUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Dao object of CommodityCrawlLog
 * Created by qinyuan on 15-3-29.
 */
public class CommodityCrawlLogDao {
    public Integer log(Integer commodityId, String action, Boolean success) {
        CommodityCrawlLog log = new CommodityCrawlLog();
        log.setCommodityId(commodityId);
        log.setAction(action);
        log.setLogTime(DateUtils.nowString());
        log.setSuccess(success);
        return HibernateUtils.save(log);
    }

    public Integer logSuccess(Integer commodityId, String action) {
        return this.log(commodityId, action, true);
    }

    public Integer logFail(Integer commodityId, String action) {
        return this.log(commodityId, action, false);
    }

    public static Factory factory() {
        return new Factory();
    }

    public static class Factory {
        private Boolean success;
        private Integer commodityId;
        private String commodityShowId;

        public Factory setSuccess(Boolean success) {
            this.success = success;
            return this;
        }

        public Factory setCommodityId(Integer commodityId) {
            this.commodityId = commodityId;
            return this;
        }

        public Factory setCommodityShowId(String commodityShowId) {
            this.commodityShowId = commodityShowId;
            return this;
        }

        public List<CommodityCrawlLog> getInstances() {
            String whereClause = "commodityId IN (SELECT id FROM Commodity)";
            if (this.success != null) {
                whereClause += " AND success=" + this.success;
            }
            if (IntegerUtils.isPositive(this.commodityId)) {
                whereClause += " AND commodityId=" + this.commodityShowId;
            }
            if (StringUtils.hasText(this.commodityShowId)) {
                whereClause += " AND commodityId IN (SELECT id FROM Commodity WHERE showId LIKE '%"
                        + StringEscapeUtils.escapeSql(this.commodityShowId) + "%')";
            }
            return HibernateUtils.getList(CommodityCrawlLog.class,
                    whereClause + " ORDER BY logTime DESC");
        }
    }
}
