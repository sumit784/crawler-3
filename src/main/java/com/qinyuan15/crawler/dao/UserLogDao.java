package com.qinyuan15.crawler.dao;

import com.qinyuan15.crawler.utils.DateUtils;
import com.qinyuan15.crawler.utils.IntegerUtils;

import java.util.List;

/**
 * Dao object of UserLog
 * Created by qinyuan on 15-3-6.
 */
public class UserLogDao {
    private final static String ORDER_CLAUSE = "ORDER BY logTime DESC";


    public static Factory factory() {
        return new Factory();
    }

    public static class Factory implements PaginationFactory<UserLog> {
        private Integer userId;

        public Factory setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public List<UserLog> getInstances(int firstResult, int maxResults) {
            return HibernateUtils.getList(UserLog.class, getWhereClause() + " " + ORDER_CLAUSE,
                    firstResult, maxResults);
        }

        private String getWhereClause() {
            if (IntegerUtils.isPositive(userId)) {
                return "userId=" + userId;
            } else {
                return "";
            }
        }

        public long getCount() {
            return HibernateUtils.getCount(UserLog.class, getWhereClause());
        }
    }

    public void save(Integer userId, String action) {
        UserLog userLog = new UserLog();
        userLog.setUserId(userId);
        userLog.setAction(action);
        userLog.setLogTime(DateUtils.nowString());
        HibernateUtils.save(userLog);
    }
}
