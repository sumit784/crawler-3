package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;

import java.util.List;

/**
 * Dao object of UserLog
 * Created by qinyuan on 15-3-6.
 */
public class UserLogDao {
    public List<UserLog> getInstances() {
        return HibernateUtil.getList(UserLog.class);
    }

    public List<UserLog> getInstancesByUserId(Integer userId) {
        return HibernateUtil.getList(UserLog.class, "userId=" + userId);
    }

    public void save(Integer userId, String action) {
        UserLog userLog = new UserLog();
        userLog.setUserId(userId);
        userLog.setAction(action);
        userLog.setLogTime(DateUtils.now());
        HibernateUtil.save(userLog);
    }
}
