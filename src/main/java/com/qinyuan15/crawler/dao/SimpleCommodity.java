package com.qinyuan15.crawler.dao;

/**
 * Class that contains basic information of Commodity
 * Created by qinyuan on 14-12-27.
 */
public class SimpleCommodity extends PersistObject {
    private String name;
    private Double price;
    private Branch branch;
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Branch getBranch() {
        return branch;
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

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
