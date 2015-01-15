package com.qinyuan15.crawler.dao;

/**
 * Class to record commodity picture information
 * Created by qinyuan on 15-1-15.
 */
public class CommodityPicture extends PersistObject {
    private Integer commodityId;
    private String url;

    public Integer getCommodityId() {
        return commodityId;
    }

    public String getUrl() {
        return url;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
