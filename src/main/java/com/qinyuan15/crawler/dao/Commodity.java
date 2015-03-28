package com.qinyuan15.crawler.dao;

/**
 * Persist Object of Commodity
 * Created by qinyuan on 14-12-28.
 */
public class Commodity extends PersistObject {

    private String serialNumber;
    private String showId;
    private String onShelfTime;
    private String url;
    private String buyUrl;
    private Boolean active;
    private Integer branchId;
    private String parameters;
    private Integer userId;
    private Integer sales;
    private String name;
    private Double price;
    private Integer categoryId;
    private Boolean inLowPrice;

    public Boolean getInLowPrice() {
        return inLowPrice;
    }

    public void setInLowPrice(Boolean inLowPrice) {
        this.inLowPrice = inLowPrice;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
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

    public String getOnShelfTime() {
        return onShelfTime == null ? null : onShelfTime.trim().replaceAll("\\s.*", "");
    }

    public Boolean getActive() {
        return active;
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

    public void setOnShelfTime(String onShelfTime) {
        this.onShelfTime = onShelfTime;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
