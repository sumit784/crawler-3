package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.dao.Proxy;

/**
 * interface to record proxy speed to database
 * Created by qinyuan on 15-2-23.
 */
public interface ProxySpeedRecorder {
    void recordSpeed(Proxy proxy, int speed);
}
