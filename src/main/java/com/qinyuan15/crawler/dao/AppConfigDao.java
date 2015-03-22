package com.qinyuan15.crawler.dao;

/**
 * Dao of AppConfig
 * Created by qinyuan on 15-3-22.
 */
public class AppConfigDao {
    public AppConfig getInstance() {
        return (AppConfig) HibernateUtils.getFirstItem("From " + AppConfig.class.getSimpleName());
    }

    public void update(AppConfig appConfig) {
        HibernateUtils.update(appConfig);
    }
}
