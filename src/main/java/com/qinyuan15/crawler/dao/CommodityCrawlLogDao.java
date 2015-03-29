package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;

import java.util.List;

/**
 * Dao object of CommodityCrawlLog
 * Created by qinyuan on 15-3-29.
 */
public class CommodityCrawlLogDao {
    public List<CommodityCrawlLog> getInstances(boolean success) {
        return HibernateUtils.getList(CommodityCrawlLog.class,
                "success=" + success + " ORDER BY logTime DESC");
    }

    public List<CommodityCrawlLog> getSuccessInstances() {
        return this.getInstances(true);
    }

    public List<CommodityCrawlLog> getFailInstances() {
        return this.getInstances(false);
    }

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
}
