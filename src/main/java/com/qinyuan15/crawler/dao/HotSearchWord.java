package com.qinyuan15.crawler.dao;

import java.sql.Date;

/**
 * Persist object of hot search word
 * Created by qinyuan on 15-2-28.
 */
public class HotSearchWord extends PersistObject {
    private String content;
    private Integer categoryId;
    private Integer searchCount;
    private Date lastTime;

    public String getContent() {
        return content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
