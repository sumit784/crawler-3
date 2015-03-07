package com.qinyuan15.crawler.dao;

import java.sql.Date;

/**
 * Persist object of user log
 * Created by qinyuan on 15-3-6.
 */
public class UserLog extends PersistObject {
    private Integer userId;
    private String action;
    private Date logTime;

    public Integer getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getUsername() {
        return new UserDao().getNameById(this.userId);
    }
}
