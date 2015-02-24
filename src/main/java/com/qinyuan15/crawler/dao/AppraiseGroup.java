package com.qinyuan15.crawler.dao;

/**
 * Persist Object of Branch
 * Created by qinyuan on 15-2-18.
 */
public class AppraiseGroup extends PersistObject {
    private Integer commodityId;
    private String content;
    private Boolean positive;

    public Integer getCommodityId() {
        return commodityId;
    }

    public String getContent() {
        return content;
    }

    public Boolean getPositive() {
        return positive;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPositive(Boolean positive) {
        this.positive = positive;
    }
}
