package com.qinyuan15.crawler.dao;

import java.sql.Date;

/**
 * Class to record price information
 * Created by qinyuan on 15-1-4.
 */
public class PriceRecord extends PersistObject {
    private int commodityId;
    private Date recordTime;
    private double price;

    public int getCommodityId() {
        return commodityId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public double getPrice() {
        return price;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
