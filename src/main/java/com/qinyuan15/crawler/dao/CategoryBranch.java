package com.qinyuan15.crawler.dao;

/**
 * Persist object of category and branch
 * Created by qinyuan on 15-3-25.
 */
public class CategoryBranch extends PersistObject implements Ranking {
    private Integer categoryId;
    private Integer branchId;
    private Integer ranking;

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
