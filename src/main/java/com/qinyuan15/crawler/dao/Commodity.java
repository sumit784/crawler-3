package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Persist Object of Commodity
 * Created by qinyuan on 14-12-28.
 */
public class Commodity extends SimpleCommodity {

    private List<String> pictures;
    private Double lowPrice;
    private String onShelfTime;
    private Double originalPrice;
    private Boolean lowestInThreeMonth;
    private PriceHistory trend;
    private String url;
    private Boolean active;
    private Integer branchId;

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public String getOnShelfTime() {
        return onShelfTime;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Boolean getLowestInThreeMonth() {
        return lowestInThreeMonth;
    }

    public PriceHistory getTrend() {
        return trend;
    }

    public Boolean getActive() {
        return active;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setOnShelfTime(String onShelfTime) {
        this.onShelfTime = onShelfTime;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setLowestInThreeMonth(Boolean lowestInThreeMonth) {
        this.lowestInThreeMonth = lowestInThreeMonth;
    }

    public void setTrend(PriceHistory trend) {
        this.trend = trend;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
