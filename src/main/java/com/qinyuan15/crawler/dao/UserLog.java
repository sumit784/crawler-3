package com.qinyuan15.crawler.dao;

/**
 * Persist object of user log
 * Created by qinyuan on 15-3-6.
 */
public class UserLog extends PersistObject {
    private Integer userId;
    private String action;
    private String logTime;

    public Integer getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public String getLogTime() {
        if (logTime == null) {
            return null;
        } else {
            return logTime.replaceAll("\\.\\d*$", "");
        }
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUsername() {
        return new UserDao().getNameById(this.userId);
    }
}
