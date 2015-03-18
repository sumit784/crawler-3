package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.core.DateUtils;

import java.util.List;

/**
 * Dao object of UserLog
 * Created by qinyuan on 15-3-6.
 */
public class UserLogDao {
    private final static String ORDER_CLAUSE = "ORDER BY logTime DESC";

    public List<UserLog> getInstances() {
        return HibernateUtil.getList(UserLog.class, ORDER_CLAUSE);
    }

    public List<UserLog> getInstancesByUserId(Integer userId) {
        return HibernateUtil.getList(UserLog.class, "userId=" + userId + " " + ORDER_CLAUSE);
    }

    public void save(Integer userId, String action) {
        UserLog userLog = new UserLog();
        userLog.setUserId(userId);
        userLog.setAction(action);
        userLog.setLogTime(DateUtils.nowString());
        HibernateUtil.save(userLog);
    }
}
