package com.qinyuan15.crawler.core.http.proxy;

import com.qinyuan15.crawler.dao.HibernateUtil;
import com.qinyuan15.crawler.dao.Proxy;

/**
 * Record proxy speed to database
 * Created by qinyuan on 15-2-23.
 */
public class DatabaseProxySpeedRecorder implements ProxySpeedRecorder {
    @Override
    public void recordSpeed(Proxy proxy, int speed) {
        if (proxy != null && speed > 0) {
            proxy.setSpeed(speed);
            HibernateUtil.update(proxy);
        }
    }
}
