package com.qinyuan15.crawler.core.crawler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to record expired commodity
 * Created by qinyuan on 15-4-7.
 */
public class ExpireCommodityRecorder {
    public final static int MAX_RETRY_TIMES = 10;
    private Map<Integer, Integer> failTimesMap = new ConcurrentHashMap<>();

    public Integer getFailTimes(Integer commodityId) {
        Integer failTimes = failTimesMap.get(commodityId);
        return failTimes == null ? 0 : failTimes;
    }

    public boolean reachMaxFailTimes(Integer commodityId) {
        Integer failTimes = getFailTimes(commodityId);
        failTimesMap.put(commodityId, ++failTimes);
        return failTimes >= MAX_RETRY_TIMES;
    }

    public void clear(Integer commodityId) {
        failTimesMap.put(commodityId, 0);
    }
}
