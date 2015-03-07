package com.qinyuan15.crawler.dao;

import java.util.List;

/**
 * Persist Object of Commodity
 * Created by qinyuan on 14-12-28.
 */
public class Commodity extends SimpleCommodity {

    private List<String> pictures;
    private String serialNumber;
    private String showId;
    private Double lowPrice;
    private String onShelfTime;
    private Double originalPrice;
    private Boolean lowestInThreeMonth;
    private PriceHistory trend;
    private String url;
    private String buyUrl;
    private Boolean active;
    private Integer branchId;
    private String parameters;
    private Integer categoryId;
    private Integer userId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getParameters() {
        return parameters;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getShowId() {
        return showId;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

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

    public Branch getBranch() {
        return new BranchDao().getInstance(this.branchId);
    }

    public Integer getUserId() {
        return userId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
